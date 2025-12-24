package com.example.authservice.exception;

import com.example.authservice.constant.Error;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class BoilerplateException extends RuntimeException {

  private static final long SERIAL_VERSION_UUID = 1L; // For serialization

  private final String errorCode;
  private final HttpStatus httpStatus;
  private final String description;

  // Constructor with error code only
  public BoilerplateException(String errorCode) {
    super(errorCode);
    this.errorCode = errorCode;
    this.httpStatus = null;
    this.description = null;
  }

  // Constructor with HttpStatus, error message, and description
  public BoilerplateException(
      HttpStatus httpStatus, String errorCode, String description, Exception exception) {
    this(errorCode, httpStatus, description, exception);
  }

  public BoilerplateException(Error error, Exception exception, Object... args) {
    this(error.getErrorCode(), error.getHttpStatus(), error.getFormattedMessage(args), exception);
  }

  public BoilerplateException(Error error, Exception exception) {
    this(error.getErrorCode(), error.getHttpStatus(), error.getMessage(), exception);
  }

  public BoilerplateException(String errorCode, HttpStatus httpStatus, String description, Exception e) {
    super(description, e);
    this.errorCode = errorCode;
    this.httpStatus = httpStatus;
    this.description = description;
  }
}
