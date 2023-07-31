package com.tecst.tecst.domain.question.dto.response;

import com.tecst.tecst.domain.question.service.dto.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class GetQuestionsResponse {
    private List<QuestionDTO> questionList;
}
