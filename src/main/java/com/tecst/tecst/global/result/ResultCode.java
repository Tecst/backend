package com.tecst.tecst.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // 도메인 별로 나눠서 관리(ex: User 도메인)
    // user
    USER_REGISTRATION_SUCCESS("U001", "사용자 등록 성공"),
    USER_USERNAME_DUPLICATED("U002", "회원 아이디 중복"),
    USER_USERNAME_NOT_DUPLICATED("U003", "회원 아이디 중복되지 않음"),
    USER_LOGIN_SUCCESS("U004", "회원 로그인 성공"),
    USER_LOGOUT_SUCCESS("U005", "회원 로그아웃 성공"),
    GET_LOGIN_USER_SUCCESS("U006", "로그인 되어있는 회원 조회 성공"),

    // questions
    STUDY_CREATE_SUCCESS("Q001", "질문 생성 성공"),
    STUDY_GET_SUCCESS("Q002", "질문 조회 성공"),
    STUDY_PAGING_GET_SUCCESS("Q003", "스터디 페이징 조회 성공"),
    USER_STUDY_PAGING_GET_SUCCESS("Q004", "유저별 스터디 페이징 조회 성공"),

    // answers(사용자가 입력한 정답)
    REGISTER_COMMENT_SUCCESS("A001", "응답 등록 성공"),
    COMMENT_FIND_SUCCESS("A002", "응답 찾기 성공"),
    ;

    private final String code;
    private final String message;
}