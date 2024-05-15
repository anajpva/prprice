package com.pprice.application.usecases.prices.params;

import java.time.Instant;

import com.pprice.application.usecases.UseCaseParams;
import com.pprice.domain.entity.Product;
import com.pprice.domain.exceptions.ValueValidationException;

public class GetProductPriceParams extends UseCaseParams {

  private final Product product;

  private final Instant date;

  public GetProductPriceParams(Product product, Instant date) {
    this.product = product;
    this.date = date;
  }

  @Override
  protected void validate() {
    if (product == null) {
      throw new ValueValidationException("Product can not be null");
    }
  }

  public Product getProduct() {
    return product;
  }

  public Instant getDate() {
    return date;
  }

}
