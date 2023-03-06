package com.tecst.tecst.domain.answer.exception;

import com.tecst.tecst.global.error.ErrorCode;
import com.tecst.tecst.global.error.exception.BusinessException;

public class AnswerNotFound extends BusinessException  {
    public AnswerNotFound() {
        super(ErrorCode.ANSWERS_NOT_FOUND_ERROR);
    }
}