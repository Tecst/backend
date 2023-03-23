package com.tecst.tecst.domain.answer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class SaveAnswerRequestDto {
    private Long userId;
    private Long commonQuestionsId;
    private String type;
    private String response;
}
