package com.starscream.soundgood.config.init;

import com.starscream.soundgood.entities.AppUser;
import com.starscream.soundgood.entities.Role;
import com.starscream.soundgood.exceptions.UnexpectedException;
import com.starscream.soundgood.repositories.RoleRepository;
import com.starscream.soundgood.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!roleRepository.existsByName("ADMIN")) {
            roleRepository.save(new Role("ADMIN"));
        }
        if (!roleRepository.existsByName("USER")) {
            roleRepository.save(new Role("USER"));
        }

        if (!userRepository.existsByUsername("root")) {
            AppUser adminUser = new AppUser();
            adminUser.setUsername("root");
            adminUser.setPassword(passwordEncoder.encode("root"));
            adminUser.setRole(roleRepository.findByName("ADMIN").orElseThrow(() -> new UnexpectedException("Cannot initialize admin user")));
            userRepository.save(adminUser);
        }
    }
}
