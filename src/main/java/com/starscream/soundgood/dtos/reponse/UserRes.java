package com.starscream.soundgood.dtos.reponse;

import com.starscream.soundgood.entities.Role;
import com.starscream.soundgood.enums.UserStatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    Long id;
    String username;
    Role role;
    UserStatusEnum status;
}
