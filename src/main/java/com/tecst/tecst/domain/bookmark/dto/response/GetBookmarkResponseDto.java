package com.tecst.tecst.domain.bookmark.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetBookmarkResponseDto {
    private Long userId;
    private List<BookmarkResponseDto> bookmarksList;

    public GetBookmarkResponseDto (Long userId, List<BookmarkResponseDto> bookmarksList) {
        this.userId = userId;
        this.bookmarksList = bookmarksList;
    }
}
