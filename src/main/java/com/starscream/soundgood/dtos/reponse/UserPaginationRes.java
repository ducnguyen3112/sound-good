package com.starscream.soundgood.dtos.reponse;

import com.starscream.soundgood.entities.AppUser;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.List;


public class UserPaginationRes extends PaginationRes<UserRes> {
    public UserPaginationRes(Page<AppUser> page) {
        super(page);
        super.setData(page.getContent().stream().map(item -> {
            UserRes userRes = new UserRes();
            BeanUtils.copyProperties(item, userRes);
            return userRes;
        }).toList());
    }

    public UserPaginationRes(List<UserRes> res, Page<AppUser> page) {
        super(res, page);

    }
}
