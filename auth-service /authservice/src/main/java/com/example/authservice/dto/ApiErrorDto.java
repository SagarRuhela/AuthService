package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorDto {

  private String message;
  private String code;
  private Instant timestamp;
  private HttpStatus httpStatus;
  private Map<String, String> errors;
  private String path;
}
