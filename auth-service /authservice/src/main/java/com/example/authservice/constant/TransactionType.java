package com.example.authservice.constant;

import lombok.Getter;

@Getter
public enum TransactionType {
  DEFAULT;

  static {
    DEFAULT.value = "Default";
  }

  private String value;
}
