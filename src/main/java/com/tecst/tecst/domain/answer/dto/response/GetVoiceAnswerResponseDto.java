package com.tecst.tecst.domain.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class GetVoiceAnswerResponseDto {
    private UUID answerId;
    private String response;

    public GetVoiceAnswerResponseDto() {}
}
