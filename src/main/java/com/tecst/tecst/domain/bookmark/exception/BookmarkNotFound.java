package com.tecst.tecst.domain.bookmark.exception;

import com.tecst.tecst.global.error.ErrorCode;
import com.tecst.tecst.global.error.exception.BusinessException;

public class BookmarkNotFound extends BusinessException {
    public BookmarkNotFound() {
        super(ErrorCode.BOOKMARKS_NOT_FOUND_ERROR);
    }
}
