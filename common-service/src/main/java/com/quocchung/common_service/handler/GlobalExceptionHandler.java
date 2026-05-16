package com.quocchung.common_service.handler;

import com.quocchung.common_service.exception.AppException;
import com.quocchung.common_service.response.ErrorResponse;
import com.quocchung.common_service.utils.ErrorCode;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  // ── Bắt tất cả AppException và các class con ──────
  // NotFoundException, ConflictException...
  @ExceptionHandler(AppException.class)
  public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
    log.error("AppException [{}]: {}", ex.getClass().getSimpleName(), ex.getMessage());
    ErrorResponse response = ErrorResponse.of(ex.getErrorCode());
    return ResponseEntity
        .status(ex.getErrorCode().getHttpStatus())
        .body(response);
  }

  // ── Bắt lỗi @Valid ────────────────────────────────
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(
      MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

    log.error("ValidationException: {}", errors);
    return ResponseEntity.badRequest()
        .body(ErrorResponse.of(ErrorCode.VALIDATION_ERROR, errors));
  }
}
