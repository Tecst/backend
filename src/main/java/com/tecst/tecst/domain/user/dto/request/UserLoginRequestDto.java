package com.tecst.tecst.domain.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLoginRequestDto {
    private String email;
    private String password;
}