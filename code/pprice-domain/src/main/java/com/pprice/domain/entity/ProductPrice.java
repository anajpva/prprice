package com.pprice.domain.entity;

import com.pprice.domain.entity.price.Price;
import com.pprice.domain.exceptions.ValueValidationException;

public record ProductPrice(Product product, Price price) {

  public ProductPrice {

    if (product == null) {
      throw new ValueValidationException("Product cannot be null");
    }

    if (price == null) {
      throw new ValueValidationException("Price cannot be null");
    }

  }
}
