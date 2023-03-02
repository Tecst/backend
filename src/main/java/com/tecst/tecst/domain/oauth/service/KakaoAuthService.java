package com.tecst.tecst.domain.oauth.service;

import com.tecst.tecst.domain.oauth.client.ClientKakao;
import com.tecst.tecst.domain.oauth.dto.AuthRequest;
import com.tecst.tecst.domain.oauth.dto.AuthResponse;
import com.tecst.tecst.domain.oauth.jwt.AuthToken;
import com.tecst.tecst.domain.oauth.jwt.AuthTokenProvider;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final ClientKakao clientKakao;
    private final AuthTokenProvider authTokenProvider;
    private final UserRepository userRepository;

    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        User kakaoMember = clientKakao.getUserData(authRequest.getAccessToken());
        String socialId = kakaoMember.getEmail();
        User member = userRepository.findByEmail(socialId);

        AuthToken appToken = authTokenProvider.createUserAppToken(socialId);

        if (member == null) {
            userRepository.save(kakaoMember);
            return AuthResponse.builder()
                    .appToken(appToken.getToken())
                    .isNewUser(Boolean.TRUE)
                    .build();
        }

        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .isNewUser(Boolean.FALSE)
                .build();
    }
}