package com.quocchung.common_service.exception;

import com.quocchung.common_service.utils.ErrorCode;

public class ConflictException extends AppException {

  public ConflictException() {
    super(ErrorCode.CONFLICT);
  }

  public ConflictException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ConflictException( String customMessage) {
    super(ErrorCode.CONFLICT, customMessage);
  }

  public ConflictException(ErrorCode errorCode, String customMessage) {
    super(errorCode, customMessage);
  }

}