package com.pprice.application.usecases.prices;

import static java.time.Instant.now;

import java.time.Instant;

import com.pprice.application.usecases.ApplicationUseCase;
import com.pprice.application.usecases.UseCase;
import com.pprice.application.usecases.prices.params.GetProductPriceParams;
import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrice;
import com.pprice.domain.entity.ProductPrices;
import com.pprice.domain.entity.price.Price;
import com.pprice.domain.exceptions.ProductPriceNotFoundException;
import com.pprice.domain.services.price.ProductPriceFetcher;

@ApplicationUseCase
public class GetProductPrice extends UseCase<GetProductPriceParams, ProductPrice> {

  private final ProductPriceFetcher productPriceFetcher;

  public GetProductPrice(ProductPriceFetcher productPriceFetcher) {
    this.productPriceFetcher = productPriceFetcher;
  }

  @Override
  protected ProductPrice doOperation(GetProductPriceParams params) {
    Instant date = params.getDate() != null ? params.getDate() : now();
    Product product = params.getProduct();

    ProductPrices productPrices = productPriceFetcher.fetch(product);
    Price price = getPriceForDate(productPrices, date);

    return new ProductPrice(product, price);
  }

  private Price getPriceForDate(ProductPrices productPrices, Instant date) {
    return productPrices.prices().stream()
        .filter(datePrice -> date.isBefore(datePrice.endDate()) && !date.isBefore(datePrice.startDate()))
        .findFirst()
        .orElseThrow(() -> new ProductPriceNotFoundException("No price available for date"));
  }

}
