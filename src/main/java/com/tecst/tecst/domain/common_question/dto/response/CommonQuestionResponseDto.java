package com.tecst.tecst.domain.common_question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.util.UUID;

public interface CommonQuestionResponseDto {
    Long getcommon_question_id();
    String getcontents();
    String getresponse();
}
