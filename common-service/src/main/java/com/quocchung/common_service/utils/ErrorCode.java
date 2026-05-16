package com.quocchung.common_service.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {


  SUCCESS(200, "Thành công", HttpStatus.OK),
  INTERNAL_SERVER_ERROR(500, "Lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR),
  VALIDATION_ERROR(400, "Dữ liệu không hợp lệ", HttpStatus.BAD_REQUEST),
  NOT_FOUND(404, "Không tìm thấy", HttpStatus.NOT_FOUND),
  UNAUTHORIZED(401, "Chưa xác thực", HttpStatus.UNAUTHORIZED),
  FORBIDDEN(403, "Không có quyền truy cập", HttpStatus.FORBIDDEN),
  CONFLICT(409, "Dữ liệu đã tồn tại", HttpStatus.CONFLICT),


  BOOK_NOT_FOUND(1001, "Không tìm thấy sách", HttpStatus.NOT_FOUND),
  BOOK_ALREADY_EXISTS(1002, "Sách đã tồn tại", HttpStatus.CONFLICT),
  BOOK_NAME_BLANK(1003, "Tên sách không được trống", HttpStatus.BAD_REQUEST),
  BOOK_AUTHOR_BLANK(1004, "Tác giả không được trống", HttpStatus.BAD_REQUEST),
  BOOK_ALREADY_DELETED(1005,"Sach đã được xóa rồi", HttpStatus.BAD_REQUEST),

  USER_NOT_FOUND(2001, "Không tìm thấy người dùng", HttpStatus.NOT_FOUND),
  USER_ALREADY_EXISTS(2002, "Email đã tồn tại", HttpStatus.CONFLICT),
  USER_LOCKED(2003, "Tài khoản bị khóa", HttpStatus.FORBIDDEN),
  USER_DELETED(2004, "Tài khoản đã bị xóa", HttpStatus.GONE),
  PASSWORD_TOO_SHORT(2005, "Mật khẩu phải từ 6 ký tự", HttpStatus.BAD_REQUEST),
  INVALID_EMAIL(2006, "Email không hợp lệ", HttpStatus.BAD_REQUEST),
  WRONG_PASSWORD(2007, "Sai mật khẩu", HttpStatus.UNAUTHORIZED),
  ;

  private final int code;
  private final String message;
  private final HttpStatus httpStatus;

  ErrorCode(int code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }
}