package com.tecst.tecst.domain.user.contoller;

import com.tecst.tecst.domain.user.dto.request.CreateUserRequestDto;
import com.tecst.tecst.domain.user.dto.response.CreateUserResponseDto;
import com.tecst.tecst.domain.user.mapper.UserMapper;
import com.tecst.tecst.domain.user.service.UserService;
import com.tecst.tecst.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tecst.tecst.global.result.ResultCode.USER_REGISTRATION_SUCCESS;

@Api(tags = "회원 API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @ApiOperation(value = "회원가입")
    @PostMapping("/new")
    public ResponseEntity<ResultResponse> registration(@RequestBody CreateUserRequestDto dto) {
        userService.register(dto);
        return ResponseEntity.ok(ResultResponse.of(USER_REGISTRATION_SUCCESS, dto));
    }

//    @ApiOperation(value = "로그인")
//    @PostMapping("/login")
//    public ResponseEntity<ResultResponse> login(
//            @RequestBody @Valid UserDto.LoginRequest loginRequest) {
//        if (!loginService.isValidUser(loginRequest)) {
//            throw new InValidPasswordException();
//        }
//
//        UserDto.RegisterResponse registerResponse =
//                userService.getUserRegisterDtoByUsername(loginRequest.getUsername());
//        loginService.login(registerResponse.getId());
//        return ResponseEntity.ok(ResultResponse.of(USER_LOGIN_SUCCESS, registerResponse));
//    }

}