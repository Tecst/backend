package com.tecst.tecst.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateUserResponseDto {
    private UUID userId;
    private String email;
    private String password;
}
