package com.tecst.tecst.domain.common_question.exception;

import com.tecst.tecst.global.error.ErrorCode;
import com.tecst.tecst.global.error.exception.BusinessException;

public class QuestionTypeNotFound extends BusinessException {
    public QuestionTypeNotFound() {
        super(ErrorCode.QUESTIONS_TYPE_NOT_FOUND_ERROR);
    }
}
