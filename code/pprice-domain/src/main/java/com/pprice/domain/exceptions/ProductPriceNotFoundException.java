package com.pprice.domain.exceptions;

public class ProductPriceNotFoundException extends RuntimeException {

  public ProductPriceNotFoundException(final String message) {
    super(message);
  }

}
