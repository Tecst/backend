package com.tecst.tecst.domain.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetAnswerResponseDto {
    private Long userId;
    private Long commonQuestionsId;
    private Long answerId;
    private String type;
    private String response;
}
