package com.tecst.tecst.domain.answer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class SaveVoiceAnswerRequestDto {
    private Long userId;
    private Long commonQuestionsId;
    private String type;
    private MultipartFile multipartFile;
}
