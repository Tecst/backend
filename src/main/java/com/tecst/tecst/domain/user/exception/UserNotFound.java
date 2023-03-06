package com.tecst.tecst.domain.user.exception;

import com.tecst.tecst.global.error.ErrorCode;
import com.tecst.tecst.global.error.exception.BusinessException;

public class UserNotFound extends BusinessException {
    public UserNotFound() {super(ErrorCode.USER_NOT_FOUND_ERROR);}
}
