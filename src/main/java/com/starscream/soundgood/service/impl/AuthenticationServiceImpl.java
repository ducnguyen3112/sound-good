package com.starscream.soundgood.service.impl;

import com.starscream.soundgood.config.securities.jwt.JwtTokenProvider;
import com.starscream.soundgood.dtos.reponse.SignInRes;
import com.starscream.soundgood.dtos.request.SignInReq;
import com.starscream.soundgood.dtos.request.SignUpReq;
import com.starscream.soundgood.entities.AppUser;
import com.starscream.soundgood.entities.Role;
import com.starscream.soundgood.exceptions.UnexpectedException;
import com.starscream.soundgood.exceptions.ValidationException;
import com.starscream.soundgood.repositories.RoleRepository;
import com.starscream.soundgood.repositories.UserRepository;
import com.starscream.soundgood.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void registerUser(SignUpReq signUpReq) {
        if (userRepository.existsByUsername(signUpReq.getUsername())) {
            throw new ValidationException("Username already exists.");
        }
        AppUser user = new AppUser();
        user.setUsername(signUpReq.getUsername());
        user.setPassword(encoder.encode(signUpReq.getPassword()));

        Role role = roleRepository.findByName("USER").orElseThrow(() -> new UnexpectedException("Role not found"));
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public SignInRes authenticateUser(SignInReq signInReq) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInReq.getUsername(), signInReq.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        AppUser user = userRepository.findByUsername(signInReq.getUsername()).get();
        String jwt = jwtTokenProvider.generateToken(authentication);
        return SignInRes.builder().token(jwt).role(user.getRole().getName()).build();
    }

}
