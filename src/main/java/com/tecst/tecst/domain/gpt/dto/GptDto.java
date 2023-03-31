package com.tecst.tecst.domain.gpt.dto;

import lombok.*;

public class GptDto {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ResGptDto {
        private String question;
        private String answer;
    }
}
