package com.tecst.tecst.domain.bookmark.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookmarkGetResponse {
    private Long userId;
    private List<BookmarkResponseDto> bookmarksList;

    public BookmarkGetResponse(Long userId, List<BookmarkResponseDto> bookmarksList) {
        this.userId = userId;
        this.bookmarksList = bookmarksList;
    }
}
