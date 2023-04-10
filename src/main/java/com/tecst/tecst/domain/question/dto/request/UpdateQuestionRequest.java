package com.tecst.tecst.domain.question.dto.request;

import com.tecst.tecst.global.util.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateQuestionRequest {
    private String content;
    private String response;
    private Type type;
}
