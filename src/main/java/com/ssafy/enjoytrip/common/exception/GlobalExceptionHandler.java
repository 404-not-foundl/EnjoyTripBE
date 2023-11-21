package com.ssafy.enjoytrip.common.exception;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public ResponseEntity<ApiResponseDto<Void>> methodValidException(MethodArgumentNotValidException e) {
        ErrorResponse responseDto = ErrorResponse.of(e.getBindingResult());
        log.error("methodValidException throw Exception : {}", e.getBindingResult());

        return ResponseEntity.badRequest().body(ResponseUtil.error(responseDto));
    }

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<ApiResponseDto<Void>> handleCustomException(CustomException e) {
        ErrorResponse responseDto = ErrorResponse.of(e.getErrorType());
        log.error("handleDataException throw Exception : {}", e.getErrorType());

        return ResponseEntity
                .status(e.getErrorType().getCode())
                .body(ResponseUtil.error(responseDto));
    }

}
