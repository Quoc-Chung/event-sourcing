package com.quocchung.common_service.handler;

import com.quocchung.common_service.exception.AppException;
import com.quocchung.common_service.response.ErrorResponse;
import com.quocchung.common_service.utils.ErrorCode;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.messaging.HandlerExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
    ErrorResponse response = ErrorResponse.of(ex.getErrorCode(), ex.getMessage());
    return ResponseEntity
        .status(ex.getErrorCode().getHttpStatus())
        .body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
    return ResponseEntity.badRequest()
        .body(ErrorResponse.of(ErrorCode.VALIDATION_ERROR, errors));
  }


  @ExceptionHandler(CommandExecutionException.class)
  public ResponseEntity<ErrorResponse> handleCommandExecutionException(CommandExecutionException ex) {
    Throwable cause = findAppExceptionCause(ex);
    if (cause instanceof AppException appEx) {
      ErrorResponse response = ErrorResponse.of(appEx.getErrorCode(), cause.getMessage());
      return ResponseEntity.status(appEx.getErrorCode().getHttpStatus()).body(response);
    }
    log.error("Unhandled CommandExecutionException: {}", ex.getMessage(), ex);
    ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus()).body(response);
  }


  @ExceptionHandler(HandlerExecutionException.class)
  public ResponseEntity<ErrorResponse> handleHandlerExecutionException(HandlerExecutionException ex) {
    Throwable cause = findAppExceptionCause(ex);
    if (cause instanceof AppException appEx) {
      ErrorResponse response = ErrorResponse.of(appEx.getErrorCode(), cause.getMessage());
      return ResponseEntity.status(appEx.getErrorCode().getHttpStatus()).body(response);
    }
    log.error("Unhandled HandlerExecutionException: {}", ex.getMessage(), ex);
    ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus()).body(response);
  }

  private Throwable findAppExceptionCause(Throwable throwable) {
    while (throwable != null) {
      if (throwable instanceof AppException) {
        return throwable;
      }
      throwable = throwable.getCause();
    }
    return null;
  }
}