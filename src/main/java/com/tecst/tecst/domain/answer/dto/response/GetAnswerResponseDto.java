package com.tecst.tecst.domain.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetAnswerResponseDto {
    private UUID userId;
    private String type;
    private Long commonQuestionsId;
    private String response;
}
