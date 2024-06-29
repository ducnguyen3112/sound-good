package com.starscream.soundgood.controllers;

import com.starscream.soundgood.dtos.reponse.ApiResponse;
import com.starscream.soundgood.dtos.reponse.UserPaginationRes;
import com.starscream.soundgood.dtos.request.UserActionReq;
import com.starscream.soundgood.dtos.request.UsersReq;
import com.starscream.soundgood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    @GetMapping
    public ApiResponse<UserPaginationRes> getUsers(UsersReq req) {
        return ApiResponse.success(userService.getUsers(req));
    }

    @PutMapping("/{id}/action")
    public ApiResponse<?> userAction(@PathVariable Long id, @RequestBody UserActionReq req) {
        userService.userAction(id, req);
        return ApiResponse.success();
    }
}
