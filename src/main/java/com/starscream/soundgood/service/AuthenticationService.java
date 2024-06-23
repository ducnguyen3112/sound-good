package com.starscream.soundgood.service;

import com.starscream.soundgood.dtos.reponse.SignInRes;
import com.starscream.soundgood.dtos.request.SignInReq;
import com.starscream.soundgood.dtos.request.SignUpReq;

public interface AuthenticationService {
    void registerUser(SignUpReq signUpReq);
    SignInRes authenticateUser(SignInReq signInReq);
}
