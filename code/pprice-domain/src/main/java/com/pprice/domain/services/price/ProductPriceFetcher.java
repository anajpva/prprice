package com.pprice.domain.services.price;

import java.util.List;

import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrices;
import com.pprice.domain.entity.price.Price;
import com.pprice.domain.exceptions.ProductNotFoundException;
import com.pprice.domain.repositories.ProductPricesRepository;
import com.pprice.domain.services.DomainService;

@DomainService
public class ProductPriceFetcher {

  private final PriceConsolidator priceConsolidator;

  private final ProductPricesRepository productPricesRepository;

  public ProductPriceFetcher(PriceConsolidator priceConsolidator, ProductPricesRepository productPricesRepository) {
    this.priceConsolidator = priceConsolidator;
    this.productPricesRepository = productPricesRepository;
  }

  public ProductPrices fetch(Product product) {
    ProductPrices productFullPrices = productPricesRepository.findByProduct(product);

    if (productFullPrices.prices().isEmpty()) {
      throw new ProductNotFoundException("No prices found for product");
    }

    List<Price> newPrices = priceConsolidator.consolidate(productFullPrices.prices());

    return new ProductPrices(product, newPrices);
  }

}
