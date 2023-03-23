package com.tecst.tecst.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
public class CreateUserRequestDto {
    @Email(message = "잘못된 이메일 형식")
    private String email;
    private String password;
}
