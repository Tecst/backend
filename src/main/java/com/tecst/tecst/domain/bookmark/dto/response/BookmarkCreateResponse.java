package com.tecst.tecst.domain.bookmark.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkCreateResponse {
    private Long bookmarkId;

    public BookmarkCreateResponse(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
}
