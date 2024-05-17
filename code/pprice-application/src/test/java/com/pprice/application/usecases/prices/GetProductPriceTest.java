package com.pprice.application.usecases.prices;

import static java.time.Duration.ofDays;
import static java.time.Duration.ofMinutes;
import static java.time.Instant.now;

import static com.pprice.application.usecases.prices.mother.GetProductPriceParamsMother.oneGetProductPriceParams;
import static com.pprice.application.usecases.prices.mother.GetProductPriceParamsMother.oneGetProductPriceParamsNoDate;
import static com.pprice.domain.entity.mother.PriceMother.onePrice;
import static com.pprice.domain.entity.mother.PriceMother.onePriceExpiration;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductPriceExpired;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductPrices;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.Instant;

import com.pprice.application.usecases.prices.params.GetProductPriceParams;
import com.pprice.domain.entity.ProductPrice;
import com.pprice.domain.entity.price.Price;
import com.pprice.domain.exceptions.ProductPriceNotFoundException;
import com.pprice.domain.services.price.ProductPriceFetcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetProductPriceTest {

  @Mock
  private ProductPriceFetcher productPriceFetcher;

  @InjectMocks
  private GetProductPrice getProductPrice;

  @Test
  void shouldThrowProductPriceNotFoundExceptionWhenNoPriceForDate() {
    GetProductPriceParams params = oneGetProductPriceParams();

    when(productPriceFetcher.fetch(params.getProduct()))
        .thenReturn(oneProductPriceExpired(params.getDate().minus(ofMinutes(10))));

    assertThrows(ProductPriceNotFoundException.class,
        () -> getProductPrice.execute(params));
  }

  @Test
  void shouldReturnProductPrice() {
    GetProductPriceParams params = oneGetProductPriceParams();
    Price expectedPrice = onePrice();

    when(productPriceFetcher.fetch(params.getProduct()))
        .thenReturn(oneProductPrices(expectedPrice));

    ProductPrice productPrice = getProductPrice.execute(params);

    assertEquals(expectedPrice, productPrice.price());
    assertEquals(params.getProduct(), productPrice.product());
  }

  @Test
  void shouldReturnCurrentProductPrice() {
    GetProductPriceParams params = oneGetProductPriceParamsNoDate();
    Instant now = now().plus(ofDays(1));
    Price expectedPrice = onePriceExpiration(now);

    when(productPriceFetcher.fetch(params.getProduct()))
        .thenReturn(oneProductPrices(expectedPrice));

    ProductPrice productPrice = getProductPrice.execute(params);

    assertEquals(expectedPrice, productPrice.price());
    assertEquals(params.getProduct(), productPrice.product());
  }

}
