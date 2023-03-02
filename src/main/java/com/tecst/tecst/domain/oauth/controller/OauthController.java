package com.tecst.tecst.domain.oauth.controller;

import com.tecst.tecst.domain.oauth.dto.ApiResponse;
import com.tecst.tecst.domain.oauth.dto.AuthRequest;
import com.tecst.tecst.domain.oauth.dto.AuthResponse;
import com.tecst.tecst.domain.oauth.jwt.AuthTokenProvider;
import com.tecst.tecst.domain.oauth.service.KakaoAuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OauthController {
    private final KakaoAuthService kakaoAuthService;
    private final AuthTokenProvider authTokenProvider;

    @ApiOperation(value = "카카오 로그인", notes = "카카오 엑세스 토큰을 이용하여 사용자 정보 받아 저장하고 앱의 토큰 신환")
    @PostMapping(value = "/kakao")
    public ResponseEntity<AuthResponse> kakaoAuthRequest(@RequestBody AuthRequest authRequest) {
        return ApiResponse.success(kakaoAuthService.login(authRequest));
    }
}
