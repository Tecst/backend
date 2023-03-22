package com.tecst.tecst.domain.bookmark.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RegistBookmarkRequestDto {
    private Long commonQuestionId;
    private Long userId;
}
