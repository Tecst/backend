package com.tecst.tecst.domain.oauth;

import com.tecst.tecst.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class ClientKakao implements ClientProxy {

    private final WebClient webClient;

    @Override
    public User getUserData(String accessToken) {
        KakaoUserResponse kakaoUserResponse = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(KakaoUserResponse.class)
                .block();

        return User.builder()
                .email(kakaoUserResponse.getKakaoAccount().getEmail())
                .build();
    }
}