package com.mustache.bbs5.domain.dto;

import com.mustache.bbs5.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinResponse {

    private Integer id;
    private String userName;
    private UserRole role;

    public static UserJoinResponse fromUser(UserDto user) {
        return new UserJoinResponse();
    }

}
