package com.my.articles.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// REST API 사용 시에 오류를 감지하는 컨트롤러
// throw new BadRequestException("TEST");
public class AopExceptionController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponseMessage> badRequestError(
            BadRequestException e
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseMessage
                        .builder()
                        .message(e.getMessage())
                        .build());
    }
}
