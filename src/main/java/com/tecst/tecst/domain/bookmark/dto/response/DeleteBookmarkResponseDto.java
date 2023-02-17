package com.tecst.tecst.domain.bookmark.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class DeleteBookmarkResponseDto {
    private UUID bookmark_id;
    private Long common_questions_id;
    private UUID user_id;

    public DeleteBookmarkResponseDto() {}
}