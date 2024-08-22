package com.example.back.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  // 회원
  KEY_NOT_FOUND(HttpStatus.UNAUTHORIZED, "키가 존재하지 않습니다."),
  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "토큰에 맞는 회원이 존재하지 않습니다.");

  private final HttpStatus httpStatus;
  private final String message;
}
