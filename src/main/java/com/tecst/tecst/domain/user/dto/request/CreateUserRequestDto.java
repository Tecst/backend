package com.tecst.tecst.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
public class CreateUserRequestDto {
    private String email;
    private String password;
//    private List<String> role;
}
