package com.tecst.tecst.domain.oauth.service;

import com.tecst.tecst.domain.oauth.client.ClientKakao;
import com.tecst.tecst.domain.oauth.dto.AuthRequest;
import com.tecst.tecst.domain.oauth.dto.AuthResponse;
import com.tecst.tecst.domain.oauth.jwt.AuthToken;
import com.tecst.tecst.domain.oauth.jwt.AuthTokenProvider;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OauthService {

    private final UserRepository userRepository;
    private final ClientKakao clientKakao;
    private final AuthTokenProvider authTokenProvider;

    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        User kakaoMember = clientKakao.getUserData(authRequest.getAccessToken());
        String email = kakaoMember.getEmail();
        User user = userRepository.findByEmail(email);

        AuthToken appToken = authTokenProvider.createUserAppToken(email);

        if (user == null) {
            userRepository.save(kakaoMember);
        }

        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .build();
    }
}
