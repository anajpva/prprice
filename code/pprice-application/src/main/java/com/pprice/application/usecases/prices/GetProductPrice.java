package com.pprice.application.usecases.prices;

import static java.time.Instant.now;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

import com.pprice.application.usecases.ApplicationUseCase;
import com.pprice.application.usecases.UseCase;
import com.pprice.application.usecases.prices.params.GetProductPriceParams;
import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrice;
import com.pprice.domain.entity.ProductPrices;
import com.pprice.domain.entity.price.Price;
import com.pprice.domain.exceptions.ProductNotFoundException;
import com.pprice.domain.exceptions.ProductPriceNotFoundException;
import com.pprice.domain.repository.ProductPricesRepository;

@ApplicationUseCase
public class GetProductPrice extends UseCase<GetProductPriceParams, ProductPrice> {

  private final ProductPricesRepository productPricesRepository;

  public GetProductPrice(ProductPricesRepository productPricesRepository) {
    this.productPricesRepository = productPricesRepository;
  }

  @Override
  protected ProductPrice doOperation(GetProductPriceParams params) {

    Instant date = params.getDate() != null ? params.getDate() : now();
    Product product = params.getProduct();

    ProductPrices productPrices = productPricesRepository.findByProduct(product);

    if (productPrices.prices().isEmpty()) {
      throw new ProductNotFoundException("No prices found for product");
    }

    Price price = getPriorityPriceForDate(productPrices, date);
    return new ProductPrice(product, price);
  }

  private Price getPriorityPriceForDate(ProductPrices productPrices, Instant date) {
    List<Price> prices = productPrices.prices().stream()
        .filter(datePrice -> date.isBefore(datePrice.endDate()) && !date.isBefore(datePrice.startDate()))
        .sorted(Comparator.comparingInt(Price::priority).reversed())
        .toList();

    if (prices.isEmpty()) {
      throw new ProductPriceNotFoundException("No price available for date");
    }

    return prices.getFirst();
  }

}
