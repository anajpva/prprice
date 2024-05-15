package com.pprice.domain.exceptions;

public class ValueValidationException extends RuntimeException {

  public ValueValidationException(final String message) {
    super(message);
  }

}
