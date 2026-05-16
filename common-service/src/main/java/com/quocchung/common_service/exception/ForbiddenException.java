package com.quocchung.common_service.exception;


import com.quocchung.common_service.utils.ErrorCode;

public class ForbiddenException extends AppException {

  public ForbiddenException() {
    super(ErrorCode.FORBIDDEN);
  }

  public ForbiddenException(ErrorCode errorCode) {
    super(errorCode);
  }
  public ForbiddenException(ErrorCode errorCode, String customMessage) {
    super(errorCode, customMessage);
  }


}
