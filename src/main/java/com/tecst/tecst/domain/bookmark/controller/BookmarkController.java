package com.tecst.tecst.domain.bookmark.controller;

import com.tecst.tecst.domain.bookmark.dto.response.DeleteBookmarkResponseDto;
import com.tecst.tecst.domain.bookmark.service.BookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(tags = "Bookmark API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/interview")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @ApiOperation(value = "북마크에서 선택한 질문 삭제")
    @DeleteMapping("/bookmarks/{id}")
    public DeleteBookmarkResponseDto DeleteBookmark(@PathVariable UUID id) {
        return bookmarkService.DeleteBookmark(id);
    }
}
