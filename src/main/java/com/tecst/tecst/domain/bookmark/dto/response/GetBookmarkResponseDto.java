package com.tecst.tecst.domain.bookmark.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class GetBookmarkResponseDto {
    private Long user_id;
    private List<BookmarkResponseDto> bookmarksList;

    public GetBookmarkResponseDto (Long user_id, List<BookmarkResponseDto> bookmarksList) {
        this.user_id = user_id;
        this.bookmarksList = bookmarksList;
    }

}
