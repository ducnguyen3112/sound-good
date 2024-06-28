package com.starscream.soundgood.service.impl;

import com.starscream.soundgood.dtos.reponse.UserPaginationRes;
import com.starscream.soundgood.dtos.request.UsersReq;
import com.starscream.soundgood.entities.AppUser;
import com.starscream.soundgood.repositories.UserRepository;
import com.starscream.soundgood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserPaginationRes getUsers(UsersReq req) {
        Pageable pageRequest = PageRequest.of(req.getPage() - 1, req.getSize());
        Page<AppUser> appUsers;

        if (StringUtils.isBlank(req.getKeyword())) {
            appUsers = userRepository.findAll(pageRequest);
        } else {
            appUsers = userRepository.findAllByUsernameContainingIgnoreCase(req.getKeyword(), pageRequest);
        }
        return new UserPaginationRes(appUsers);
    }
}
