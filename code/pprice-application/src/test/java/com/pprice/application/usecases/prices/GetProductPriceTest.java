package com.pprice.application.usecases.prices;

import static java.time.Duration.ofDays;
import static java.time.Duration.ofMinutes;
import static java.time.Instant.now;

import static com.pprice.application.usecases.prices.mother.GetProductPriceParamsMother.oneGetProductPriceParams;
import static com.pprice.application.usecases.prices.mother.GetProductPriceParamsMother.oneGetProductPriceParamsNoDate;
import static com.pprice.domain.entity.mother.PriceMother.onePrice;
import static com.pprice.domain.entity.mother.PriceMother.onePriceExpiration;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductNoPrices;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductPriceExpired;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductPrices;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.Instant;

import com.pprice.application.usecases.prices.params.GetProductPriceParams;
import com.pprice.domain.entity.ProductPrice;
import com.pprice.domain.entity.price.Price;
import com.pprice.domain.exceptions.ProductNotFoundException;
import com.pprice.domain.exceptions.ProductPriceNotFoundException;
import com.pprice.domain.repository.ProductPricesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetProductPriceTest {

  @Mock
  private ProductPricesRepository productPricesRepository;

  private GetProductPrice getProductPrice;

  @BeforeEach
  void beforeEach() {

    getProductPrice = new GetProductPrice(productPricesRepository);
  }

  @Test
  void shouldThrowProductNotFoundExceptionWhenNoPrices() {
    GetProductPriceParams params = oneGetProductPriceParams();

    when(productPricesRepository.findByProduct(params.getProduct()))
        .thenReturn(oneProductNoPrices());

    assertThrows(ProductNotFoundException.class,
        () -> getProductPrice.execute(params));
  }

  @Test
  void shouldThrowProductPriceNotFoundExceptionWhenNoPriceForDate() {
    GetProductPriceParams params = oneGetProductPriceParams();

    when(productPricesRepository.findByProduct(params.getProduct()))
        .thenReturn(oneProductPriceExpired(params.getDate().minus(ofMinutes(10))));

    assertThrows(ProductPriceNotFoundException.class,
        () -> getProductPrice.execute(params));
  }

  @Test
  void shouldReturnProductPrice() {
    GetProductPriceParams params = oneGetProductPriceParams();
    Price expectedPrice = onePrice();

    when(productPricesRepository.findByProduct(params.getProduct()))
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

    when(productPricesRepository.findByProduct(params.getProduct()))
        .thenReturn(oneProductPrices(expectedPrice));

    ProductPrice productPrice = getProductPrice.execute(params);

    assertEquals(expectedPrice, productPrice.price());
    assertEquals(params.getProduct(), productPrice.product());
  }

}
