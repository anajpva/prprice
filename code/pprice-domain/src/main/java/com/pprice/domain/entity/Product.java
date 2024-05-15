package com.pprice.domain.entity;

import com.pprice.domain.exceptions.ValueValidationException;

public record Product(Integer productId, Integer brandId) {

  public Product {

    if (productId == null) {
      throw new ValueValidationException("Product id cannot be null");
    }

    if (brandId == null) {
      throw new ValueValidationException("Cone cannot be null");
    }

  }

}
