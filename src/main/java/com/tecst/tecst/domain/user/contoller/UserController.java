package com.tecst.tecst.domain.user.contoller;

import com.tecst.tecst.domain.auth.dto.Logout;
import com.tecst.tecst.domain.auth.dto.Reissue;
import com.tecst.tecst.domain.auth.dto.TokenInfo;
import com.tecst.tecst.domain.user.dto.request.CreateUserRequestDto;
import com.tecst.tecst.domain.user.dto.request.UserLoginRequestDto;
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

import javax.validation.Valid;

import static com.tecst.tecst.global.result.ResultCode.USER_REGISTRATION_SUCCESS;

@Api(tags = "User API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/new")
    public ResponseEntity<ResultResponse> registration(@Valid @RequestBody CreateUserRequestDto dto) {
        userService.register(dto);
        return ResponseEntity.ok(ResultResponse.of(USER_REGISTRATION_SUCCESS, dto));
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginRequestDto dto) {
        return userService.login(dto);
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> login(@RequestBody Logout dto) {
        return userService.logout(dto);
    }

    @ApiOperation(value = "Token 재발급")
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody Reissue reissue) {
        return userService.reissue(reissue);
    }
}