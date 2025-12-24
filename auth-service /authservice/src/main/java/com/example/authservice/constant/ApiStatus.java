package com.example.authservice.constant;

import lombok.Getter;

@Getter
public enum ApiStatus {
    SUCCESS, FAILURE;

    static {
        SUCCESS.value = "Success";
        FAILURE.value = "Failure";
    }

    private String value;
}
