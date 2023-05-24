package com.tecst.tecst.domain.bookmark.dto.response;

import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.global.util.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponseDto {
    private Long questionId;
    private Long bookmarkId;
    private String content;
    private String response;
    private Type type;




    public static BookmarkResponseDto toBookmarkResponseDto(Bookmark bookmark) {
        return new BookmarkResponseDto(
                bookmark.getQuestion().getQuestionId(),
                bookmark.getBookmarkId(),
                bookmark.getQuestion().getContent(),
                bookmark.getQuestion().getResponse(),
                bookmark.getQuestion().getType());
    }
}
