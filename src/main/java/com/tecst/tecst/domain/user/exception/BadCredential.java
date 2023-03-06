package com.tecst.tecst.domain.user.exception;

import com.tecst.tecst.global.error.ErrorCode;
import com.tecst.tecst.global.error.exception.BusinessException;

public class BadCredential extends BusinessException {
    public BadCredential() {super(ErrorCode.BAD_CREDENTIAL_ERROR);}
}
