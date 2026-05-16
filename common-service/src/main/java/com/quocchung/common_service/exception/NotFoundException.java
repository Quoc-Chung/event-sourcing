package com.quocchung.common_service.exception;

import com.quocchung.common_service.utils.ErrorCode;

public class NotFoundException extends AppException {

  public NotFoundException() {
    super(ErrorCode.NOT_FOUND);
  }

  public NotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

  public NotFoundException( String customMessage) {
    super(ErrorCode.NOT_FOUND, customMessage);
  }

  public NotFoundException(ErrorCode errorCode, String customMessage) {
    super(errorCode, customMessage);
  }
}

