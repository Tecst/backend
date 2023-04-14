package com.tecst.tecst.domain.bookmark.mapper;

import com.tecst.tecst.domain.bookmark.dto.request.RegistBookmarkRequestDto;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.question.exception.QuestionNotFound;
import com.tecst.tecst.domain.question.repository.QuestionRepository;
import com.tecst.tecst.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkMapper {
    private final UserRepository userRepository;
    private final QuestionRepository commonQuestionRepository;

    public RegistBookmarkRequestDto toDto(Bookmark bookmark) {
        return RegistBookmarkRequestDto.builder().userId(bookmark.getUser().getUserId())
                .questionId(bookmark.getQuestion().getQuestionId()).build();
    }

    public Bookmark toEntity(RegistBookmarkRequestDto dto) {
        Bookmark bookmark = Bookmark.builder().user(userRepository.findByUserId(dto.getUserId()))
                .question(commonQuestionRepository.findByQuestionId(dto.getQuestionId()).orElseThrow(QuestionNotFound::new)).build();
        return bookmark;
    }
}
