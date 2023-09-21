package com.tecst.tecst.domain.question.dto.response;

import com.tecst.tecst.domain.question.entity.Question;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class QuestionResponseDTO {
    private Long questionId;
    private String response;

    // TODO Mapper로 변경
    public static QuestionResponseDTO questionResponseMapping(Question question) {
        return QuestionResponseDTO.builder()
                .questionId(question.getQuestionId())
                .response(question.getResponse())
                .build();
    }
}