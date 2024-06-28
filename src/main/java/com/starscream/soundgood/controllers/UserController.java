package com.starscream.soundgood.controllers;

import com.starscream.soundgood.dtos.reponse.ApiResponse;
import com.starscream.soundgood.dtos.reponse.UserPaginationRes;
import com.starscream.soundgood.dtos.request.UsersReq;
import com.starscream.soundgood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    @GetMapping
    public ApiResponse<UserPaginationRes> getSounds(UsersReq req) {
        return ApiResponse.success(userService.getUsers(req));
    }
}
