package com.quocchung.common_service.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quocchung.common_service.utils.ErrorCode;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

  private int code;
  private String message;
  private HttpStatus status;
  private Map<String, String> errors;
  private LocalDateTime timestamp;

  public static ErrorResponse of(ErrorCode errorCode) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .message(errorCode.getMessage())
        .status(errorCode.getHttpStatus())
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static ErrorResponse of(ErrorCode errorCode, String customMessage) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .message(customMessage)
        .status(errorCode.getHttpStatus())
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static ErrorResponse of(ErrorCode errorCode, Map<String, String> errors) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .message(errorCode.getMessage())
        .status(errorCode.getHttpStatus())
        .errors(errors)
        .timestamp(LocalDateTime.now())
        .build();
  }
}

