package com.starscream.soundgood.controllers;

import com.starscream.soundgood.dtos.reponse.ApiResponse;
import com.starscream.soundgood.dtos.reponse.SignInRes;
import com.starscream.soundgood.dtos.request.SignInReq;
import com.starscream.soundgood.dtos.request.SignUpReq;
import com.starscream.soundgood.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApiResponse<SignInRes> registerUser(@RequestBody SignUpReq signUpReq) {
        authenticationService.registerUser(signUpReq);
        return ApiResponse.success();
    }

    @PostMapping("/login")
    public ApiResponse<SignInRes> authenticateUser(@RequestBody SignInReq signInReq) {
        return ApiResponse.success(authenticationService.authenticateUser(signInReq));
    }
}
