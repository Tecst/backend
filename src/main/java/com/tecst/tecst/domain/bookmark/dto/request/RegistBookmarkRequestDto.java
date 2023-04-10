package com.tecst.tecst.domain.bookmark.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class RegistBookmarkRequestDto {
    private Long questionId;
    private Long userId;
}
