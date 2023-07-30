package com.tecst.tecst.domain.bookmark.mapper;

import com.tecst.tecst.domain.bookmark.dto.request.RegistBookmarkRequestDto;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.question.exception.QuestionNotFound;
import com.tecst.tecst.domain.question.repository.QuestionRepository;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkMapper {
    private final UserRepository userRepository;
    private final QuestionRepository commonQuestionRepository;

    public RegistBookmarkRequestDto toDto(Bookmark bookmark) {
        return new RegistBookmarkRequestDto(bookmark.getQuestion().getQuestionId());
    }

    public Bookmark toEntity(Question question, User user) {
        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .question(question).build();
        return bookmark;
    }
}
