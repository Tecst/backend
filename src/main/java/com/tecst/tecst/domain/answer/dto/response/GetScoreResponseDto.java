package com.tecst.tecst.domain.answer.dto.response;

import com.tecst.tecst.domain.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetScoreResponseDto {
    private Long answerId;
    private int score;

    public static GetScoreResponseDto from(Answer answer){
        return new GetScoreResponseDto(answer.getAnswerId(),
                answer.getScore());
    }
}
