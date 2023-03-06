package com.tecst.tecst.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Email;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
public class CreateUserRequestDto {
    @Email(message = "잘못된 이메일 형식")
    private String email;
    private String password;
}
