package com.example.authservice.constant;

import lombok.Getter;

@Getter
public enum ErrorCategory {
    SYSTEM, BUSINESS, THIRD_PARTY;

    static {
        SYSTEM.value = "System";
        BUSINESS.value = "Business";
        THIRD_PARTY.value = "Third Party";
    }

    private String value;
}
