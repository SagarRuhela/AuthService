package com.example.authservice.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.example.authservice.constant.ApplicationConstant.ERROR_CODE_PREFIX;


@Getter
public enum Error {
    BAD_REQUEST_INPUT_REQUIRED(
            HttpStatus.BAD_REQUEST, ERROR_CODE_PREFIX + "001", "%s Input required."),
    PRECONDITION_FAILED_CUSTOM_MESSAGE(HttpStatus.PRECONDITION_FAILED, ERROR_CODE_PREFIX + "002", "%s"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_CODE_PREFIX + "009", " %s ");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    Error(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * Formats the message with the given arguments.
     *
     * @param args The arguments to be used in formatting the message.
     * @return The formatted message string.
     */
    public String getFormattedMessage(Object... args) {
        return String.format(this.message, args);
    }

}
