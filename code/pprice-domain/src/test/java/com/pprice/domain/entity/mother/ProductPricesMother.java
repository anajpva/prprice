package com.pprice.domain.entity.mother;

import static com.pprice.domain.entity.mother.PriceMother.onePrice;
import static com.pprice.domain.entity.mother.PriceMother.onePriceExpiration;
import static com.pprice.domain.entity.mother.ProductMother.oneProduct;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrice;
import com.pprice.domain.entity.ProductPrices;
import com.pprice.domain.entity.price.Price;

public class ProductPricesMother {

  public static ProductPrice oneProductPrice(Product product) {
    return new ProductPrice(product, onePrice());
  }

  public static ProductPrice oneProductPrice() {
    return oneProductPrice(oneProduct());
  }

  public static ProductPrices oneProductPrices() {
    return new ProductPrices(oneProduct(), List.of(onePrice()));
  }

  public static ProductPrices oneProductPrices(Price price) {
    return oneProductPrices(List.of(price));
  }

  public static ProductPrices oneProductPrices(Collection<Price> prices) {
    return new ProductPrices(oneProduct(), prices);
  }

  public static ProductPrices oneProductNoPrices() {
    return new ProductPrices(oneProduct(), List.of());
  }

  public static ProductPrices oneProductPriceExpired(Instant expirationDate) {
    return new ProductPrices(oneProduct(), List.of(onePriceExpiration(expirationDate)));
  }

}