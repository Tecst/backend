package com.tecst.tecst.domain.user.exception;

import com.tecst.tecst.global.error.ErrorCode;
import com.tecst.tecst.global.error.exception.BusinessException;

public class EmailDuplicated extends BusinessException {
    public EmailDuplicated() {
        super(ErrorCode.DUPLICATED_EMAIL);
    }
}
