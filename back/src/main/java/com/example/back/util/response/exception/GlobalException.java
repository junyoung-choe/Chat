package com.example.back.util.response.exception;

import com.example.back.util.response.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GlobalException extends RuntimeException {
  ErrorCode errorCode;
}
