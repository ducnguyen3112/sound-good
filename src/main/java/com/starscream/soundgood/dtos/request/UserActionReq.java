package com.starscream.soundgood.dtos.request;

import com.starscream.soundgood.enums.ActionEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserActionReq {
    ActionEnum action;
}
