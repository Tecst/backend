package com.tecst.tecst.domain.bookmark.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class DeleteBookmarkResponseDto {
    private Long bookmark_id;
    private Long common_questions_id;
    private Long user_id;

    public DeleteBookmarkResponseDto() {}
}