package com.tecst.tecst.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateUserResponseDto {
    private Long userId;
    private String email;
    private String password;
}
