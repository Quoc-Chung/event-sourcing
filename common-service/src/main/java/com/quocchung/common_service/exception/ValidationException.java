package com.quocchung.common_service.exception;


import com.quocchung.common_service.utils.ErrorCode;

public class ValidationException extends AppException {

  public ValidationException() {
    super(ErrorCode.VALIDATION_ERROR);
  }

  public ValidationException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ValidationException(String customMessage) {
    super(ErrorCode.VALIDATION_ERROR, customMessage);
  }

  public ValidationException(ErrorCode errorCode, String customMessage) {
    super(errorCode, customMessage);
  }


}
