package com.pprice.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(final String message) {
    super(message);
  }

}
