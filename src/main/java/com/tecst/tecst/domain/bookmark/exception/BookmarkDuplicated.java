package com.tecst.tecst.domain.bookmark.exception;

import com.tecst.tecst.global.error.ErrorCode;
import com.tecst.tecst.global.error.exception.BusinessException;

public class BookmarkDuplicated extends BusinessException {
    public BookmarkDuplicated() {super(ErrorCode.BOOKMARKS_DUPLICATED);}
}
