package com.tecst.tecst.domain.answer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SaveAnswerRequestDto {
    private UUID user_id;
    private String type;
    private Long common_questions_id;
    private String response;
}
