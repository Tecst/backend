package com.tecst.tecst.domain.gpt.controller;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.tecst.tecst.domain.gpt.dto.GptDto.*;


@RestController
@RequiredArgsConstructor
public class GptController {
    private final ChatgptService chatgptService;

    @PostMapping("api/v1/gpt")
    public ResGptDto sendQuestion(@RequestParam String question) {
        String answer = chatgptService.sendMessage(question).replaceAll("\n", " ").replaceAll("\n\n", " ");
        return ResGptDto.builder().question(question).answer(answer).build();
    }
}
