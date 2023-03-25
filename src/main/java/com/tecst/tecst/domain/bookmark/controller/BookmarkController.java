package com.tecst.tecst.domain.bookmark.controller;

import com.tecst.tecst.domain.bookmark.dto.request.RegistBookmarkRequestDto;
import com.tecst.tecst.domain.bookmark.dto.response.DeleteBookmarkResponseDto;
import com.tecst.tecst.domain.bookmark.service.BookmarkService;
import com.tecst.tecst.domain.common_question.service.CommonQuestionService;
import com.tecst.tecst.domain.user.service.UserService;
import com.tecst.tecst.global.result.ResultResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.tecst.tecst.global.result.ResultCode.BOOKMARK_REGISTRATION_SUCCESS;

@Api(tags = "Bookmark API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)


@RequestMapping("/api/v1/interview/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final CommonQuestionService commonQuestionService;
    private final UserService userService;

    @ApiOperation(value = "북마크 등록")
    @PostMapping("/new")
    public ResponseEntity<ResultResponse> registBookmark(RegistBookmarkRequestDto dto) {
        commonQuestionService.findCommonQuestionById(dto.getCommonQuestionId());
        userService.findUserById(dto.getUserId());
        bookmarkService.register(dto);
        return ResponseEntity.ok(ResultResponse.of(BOOKMARK_REGISTRATION_SUCCESS, dto));
    }

    @ApiOperation(value = "북마크에서 선택한 질문 삭제")
    @DeleteMapping("/{id}")
    public DeleteBookmarkResponseDto DeleteBookmark(@PathVariable Long id) {
        return bookmarkService.DeleteBookmark(id);
    }
}
