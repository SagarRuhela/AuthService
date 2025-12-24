package com.example.authservice.constant;

public class ApplicationConstant {
  // Private constructor to prevent instantiation
  private ApplicationConstant() {
    // Exception to prevent reflection-based instantiation
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  // Application Constant
  public static final String ERROR_CODE_PREFIX = "SPI";
  // @Value("${frontend.origin}")
  public static String frontendOrigin="http://localhost:3000";
  // Http Status Code
  public static final String HTTP_RESPONSE_CODE_200 = "200";
  public static final String HTTP_RESPONSE_CODE_201 = "201";
  public static final String HTTP_RESPONSE_CODE_401 = "401";
  public static final String HTTP_RESPONSE_CODE_403 = "403";
  public static final String HTTP_RESPONSE_CODE_404 = "404";
  public static final String HTTP_RESPONSE_CODE_400 = "400";
  public static final String HTTP_RESPONSE_CODE_500 = "500";
  // Http Status Message
  public static final String HTTP_RESPONSE_CODE_200_MESSAGE = "The request has succeeded";
  public static final String HTTP_RESPONSE_CODE_201_MESSAGE =
      "The request has succeeded and a new resource has been created";
  public static final String HTTP_RESPONSE_CODE_401_MESSAGE =
      "The request requires user authentication";
  public static final String HTTP_RESPONSE_CODE_403_MESSAGE =
      "The server understood the request, but is refusing to fulfill it";
  public static final String HTTP_RESPONSE_CODE_404_MESSAGE =
      "The server has not found anything matching the Request-URI";
  public static final String HTTP_RESPONSE_CODE_400_MESSAGE =
      "The server cannot process the request due to a client error";
  public static final String HTTP_RESPONSE_CODE_500_MESSAGE = "The server has an unexpected error";

  public static final String HEADER_KEY_ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
  public static final String HEADER_KEY_ACCESS_CONTROL_ALLOW_METHODS =
      "Access-Control-Allow-Methods";
  public static final String HEADER_KEY_ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
  public static final String HEADER_KEY_ACCESS_CONTROL_ALLOW_HEADERS =
      "Access-Control-Allow-Headers";
  public static final String HEADER_KEY_CONTENT_TYPE = "Content-Type";
  public static final String HEADER_VALUE_ACCESS_CONTROL_ALLOW_ORIGIN =frontendOrigin ;
  public static final String HEADER_VALUE_ACCESS_CONTROL_ALLOW_METHODS =
      "POST, GET, PUT, PATCH, DELETE, OPTIONS";
  public static final String HEADER_VALUE_ACCESS_CONTROL_MAX_AGE = "3600";
  public static final String HEADER_VALUE_ACCESS_CONTROL_ALLOW_HEADERS =
      "x-requested-with, authorization, content-type, X-Correlation-ID, X-Transaction-ID, X-Transaction-TYPE, lara-api-key, current-timestamp";
  public static final String HEADER_VALUE_CONTENT_TYPE = "application/json";

  public static final String HTTP_METHOD_OPTIONS = "OPTIONS";
  public static final String PATH_API_PREFIX = "v1";
}
