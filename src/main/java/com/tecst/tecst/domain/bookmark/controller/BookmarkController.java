package com.tecst.tecst.domain.bookmark.controller;

import com.tecst.tecst.domain.bookmark.dto.request.RegistBookmarkRequestDto;
import com.tecst.tecst.domain.bookmark.dto.response.BookmarkResponseDto;
import com.tecst.tecst.domain.bookmark.dto.response.DeleteBookmarkResponseDto;
import com.tecst.tecst.domain.bookmark.dto.response.GetBookmarkResponseDto;
import com.tecst.tecst.domain.bookmark.service.BookmarkService;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.question.service.QuestionService;
import com.tecst.tecst.domain.user.service.UserService;
import com.tecst.tecst.global.result.ResultResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tecst.tecst.global.result.ResultCode.BOOKMARK_REGISTRATION_SUCCESS;

@Api(tags = "Bookmark API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)


@RequestMapping("/api/v1/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final QuestionService questionService;

    @ApiOperation(value = "북마크 등록")
    @PostMapping
    public ResponseEntity<ResultResponse> registBookmark(@RequestBody RegistBookmarkRequestDto dto) {
        bookmarkService.register(dto);
        return ResponseEntity.ok(ResultResponse.of(BOOKMARK_REGISTRATION_SUCCESS, dto));
    }

    @ApiOperation(value = "북마크에서 선택한 질문 삭제")
    @DeleteMapping("/{id}")
    public DeleteBookmarkResponseDto DeleteBookmark(@PathVariable Long id) {
        return bookmarkService.DeleteBookmark(id);
    }

    @GetMapping
    @ApiOperation(value = "특정 사용자의 모든 북마크 조회")
    public List<BookmarkResponseDto> GetBookmark() {
        return bookmarkService.GetBookmarks();
    }
}
