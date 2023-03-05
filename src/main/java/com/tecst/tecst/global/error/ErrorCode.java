package com.tecst.tecst.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** {주체}_{이유} message 는 동사 명사형으로 마무리 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
    INPUT_INVALID_VALUE(409, "G002", "잘못된 입력"),

    // User 도메인
    USER_NOT_FOUND_ERROR(400, "U001", "사용자를 찾을 수 없음"),
    UNAUTHORIZED_ACCESS_ERROR(403, "U002", "승인되지 않은 접근"),
    USER_USERNAME_DUPLICATED(409, "U003", "회원 아이디 중복"),
    INCORRECT_EMAIL_FORMAT(400, "U004", "잘못된 이메일 형식"),
    DUPLICATED_EMAIL(400, "U005", "이메일 중복"),

    // CommonQuestion
    QUESTIONS_TYPE_NOT_FOUND_ERROR(400, "Q001", "질문 형식을 찾을 수 없음"),
    QUESTIONS_NOT_FOUND_ERROR(400, "Q002", "질문을 찾을 수 없음");


    // Comment

    private final int status;
    private final String code;
    private final String message;
}