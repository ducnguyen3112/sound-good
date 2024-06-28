package com.starscream.soundgood.service;

import com.starscream.soundgood.dtos.reponse.UserPaginationRes;
import com.starscream.soundgood.dtos.request.UsersReq;

public interface UserService {
    UserPaginationRes getUsers(UsersReq req);
}
