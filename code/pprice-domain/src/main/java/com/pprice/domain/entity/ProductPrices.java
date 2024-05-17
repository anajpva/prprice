package com.pprice.domain.entity;

import java.util.Collection;

import com.pprice.domain.entity.price.Price;
import com.pprice.domain.exceptions.ValueValidationException;

public record ProductPrices(Product product, Collection<Price> prices) {

  public ProductPrices {

    if (product == null) {
      throw new ValueValidationException("Product cannot be null");
    }

    if (prices == null) {
      throw new ValueValidationException("Price list cannot be null");
    }

  }
}
