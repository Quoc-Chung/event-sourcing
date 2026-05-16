package com.quocchung.common_service.exception;

import com.quocchung.common_service.utils.ErrorCode;

public class UnauthorizedException extends AppException {

  public UnauthorizedException() {
    super(ErrorCode.UNAUTHORIZED);
  }

  public UnauthorizedException(ErrorCode errorCode) {
    super(errorCode);
  }
  public  UnauthorizedException( String customMessage) {
    super(ErrorCode.UNAUTHORIZED, customMessage);
  }

  public  UnauthorizedException(ErrorCode errorCode, String customMessage) {
    super(errorCode, customMessage);
  }
}
