package com.tecst.tecst.domain.question.exception;

import com.tecst.tecst.global.error.ErrorCode;
import com.tecst.tecst.global.error.exception.BusinessException;

public class QuestionNotFound extends BusinessException {
    public QuestionNotFound() {
        super(ErrorCode.QUESTIONS_NOT_FOUND_ERROR);
    }
}
