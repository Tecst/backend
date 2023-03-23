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
    private Long user_id;
    private String type;
    private Long common_questions_id;
    private String response;
}
