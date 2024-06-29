package com.starscream.soundgood.service.impl;

import com.starscream.soundgood.config.context.UserContext;
import com.starscream.soundgood.dtos.reponse.UserPaginationRes;
import com.starscream.soundgood.dtos.request.UserActionReq;
import com.starscream.soundgood.dtos.request.UsersReq;
import com.starscream.soundgood.entities.AppUser;
import com.starscream.soundgood.enums.ActionEnum;
import com.starscream.soundgood.enums.UserStatusEnum;
import com.starscream.soundgood.exceptions.ValidationException;
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
    private final UserContext userContext;

    @Override
    public UserPaginationRes getUsers(UsersReq req) {
        Pageable pageRequest = PageRequest.of(req.getPage() - 1, req.getSize());
        Page<AppUser> appUsers;

        if (StringUtils.isBlank(req.getKeyword())) {
            appUsers = userRepository.findAllByIdNot(userContext.getUser().getId(), pageRequest);
        } else {
            appUsers = userRepository.findAllByUsernameContainingIgnoreCaseAndIdNot(req.getKeyword(), userContext.getUser().getId(), pageRequest);
        }
        return new UserPaginationRes(appUsers);
    }

    @Override
    public void userAction(Long id, UserActionReq req) {
        AppUser user = userRepository.findById(id).orElseThrow(() -> new ValidationException("User Not Found"));
        if (req.getAction() == ActionEnum.DEACTIVATE) {
            user.setStatus(UserStatusEnum.INACTIVE);
        } else {
            user.setStatus(UserStatusEnum.ACTIVE);
        }
        userRepository.save(user);
    }
}
