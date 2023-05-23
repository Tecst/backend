package com.tecst.tecst.domain.answer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveScoreRequestDto {
    private Long answerId;
    private String feedBack;
}
