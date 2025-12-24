package com.example.authservice.exception;


import com.example.authservice.dto.ApiErrorDto;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(BoilerplateException.class)
  public ResponseEntity<ApiErrorDto> handleCustomException(
      BoilerplateException ex, WebRequest request) {
    log.error("Handling CustomException: {}", ex.getMessage());
    HttpStatus httpStatus =
        (ObjectUtils.isEmpty(ex.getHttpStatus()))
            ? HttpStatus.INTERNAL_SERVER_ERROR
            : ex.getHttpStatus();
    ApiErrorDto apiErrorDto = new ApiErrorDto();
    apiErrorDto.setHttpStatus(ex.getHttpStatus());
    apiErrorDto.setMessage(ex.getDescription());
    apiErrorDto.setCode(ex.getErrorCode());
    apiErrorDto.setTimestamp(Instant.now());
    apiErrorDto.setPath(request.getDescription(false));
    return new ResponseEntity<>(apiErrorDto, httpStatus);
  }



  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ApiErrorDto> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex, WebRequest request) {
    log.error("Type mismatch: {}", ex.getMessage());
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    String message =
        String.format(
            "Parameter '%s' should be of type '%s'",
            ex.getName(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
    return getApiErrorResponseEntity(httpStatus, message, request);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiErrorDto> handleResourceNotFound(ResourceNotFoundException ex) {
    log.error("Resource not found: {}", ex.getMessage());
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    ApiErrorDto apiErrorDto = new ApiErrorDto();
    apiErrorDto.setHttpStatus(httpStatus);
    apiErrorDto.setMessage(ex.getMessage());
    apiErrorDto.setCode(String.valueOf(httpStatus.value()));
    apiErrorDto.setTimestamp(Instant.now());

    return new ResponseEntity<>(apiErrorDto, httpStatus);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorDto> handleGlobalException(Exception ex, WebRequest request) {
    log.error("Unexpected exception occurred", ex);
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    String message =
        !StringUtils.isEmpty(ex.getMessage()) ? ex.getMessage() : ex.getCause().toString();
    return getApiErrorResponseEntity(httpStatus, message, request);
  }

  private ResponseEntity<ApiErrorDto> getApiErrorResponseEntity(
      HttpStatus httpStatus, String message, WebRequest request) {
    ApiErrorDto apiErrorDto = new ApiErrorDto();
    apiErrorDto.setHttpStatus(httpStatus);
    apiErrorDto.setMessage(message);
    apiErrorDto.setPath(request.getDescription(false));
    apiErrorDto.setCode(String.valueOf(httpStatus.value()));
    apiErrorDto.setTimestamp(Instant.now());
    return new ResponseEntity<>(apiErrorDto, httpStatus);
  }
  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ApiErrorDto> handleUsernameNotFound(
          UsernameNotFoundException ex, WebRequest request) {

    log.error("Username not found: {}", ex.getMessage());
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    ApiErrorDto apiErrorDto = new ApiErrorDto();
    apiErrorDto.setHttpStatus(httpStatus);
    apiErrorDto.setMessage(ex.getMessage()); // custom message
    apiErrorDto.setPath(request.getDescription(false));
    apiErrorDto.setCode(String.valueOf(httpStatus.value()));
    apiErrorDto.setTimestamp(Instant.now());

    return new ResponseEntity<>(apiErrorDto, httpStatus);
  }



}
