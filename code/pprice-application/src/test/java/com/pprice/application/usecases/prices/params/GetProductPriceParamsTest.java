package com.pprice.application.usecases.prices.params;

import static java.time.Instant.now;

import static com.pprice.domain.entity.mother.ProductMother.oneProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import com.pprice.domain.entity.Product;
import com.pprice.domain.exceptions.ValueValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetProductPriceParamsTest {

  @Test
  void shouldCreateParams() {
    Product product = oneProduct();
    Instant date = now();

    GetProductPriceParams result = new GetProductPriceParams(product, date);
    result.validate();

    assertEquals(product, result.getProduct());
    assertEquals(date, result.getDate());
  }

  @Test
  void shouldCreateParamsWhenNoDate() {
    Product product = oneProduct();

    GetProductPriceParams result = new GetProductPriceParams(product, null);
    result.validate();

    assertEquals(product, result.getProduct());
    assertNull(result.getDate());
  }

  @Test
  void throwsErrorWhenProductIsNull() {
    GetProductPriceParams result = new GetProductPriceParams(null, now());

    assertThrows(ValueValidationException.class, result::validate);
  }

}
