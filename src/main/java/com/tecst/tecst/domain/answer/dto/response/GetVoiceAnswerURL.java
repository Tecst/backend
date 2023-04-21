package com.tecst.tecst.domain.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class GetVoiceAnswerURL {
    private String s3FileName;
    private String answerURL;

    public GetVoiceAnswerURL() {}
}

