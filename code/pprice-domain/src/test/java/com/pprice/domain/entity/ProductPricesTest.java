package com.pprice.domain.entity;

import static com.pprice.domain.entity.mother.PriceMother.onePrice;
import static com.pprice.domain.entity.mother.ProductMother.oneProduct;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductNoPrices;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductPrices;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import com.pprice.domain.entity.price.Price;
import com.pprice.domain.exceptions.ValueValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductPricesTest {

  @Test
  void shouldConstructWithEmptyPriceList() {
    Product product = oneProduct();

    ProductPrices result = new ProductPrices(product, List.of());

    assertEquals(product, result.product());
    assertEquals(List.of(), result.prices());
  }

  @Test
  void shouldConstructWithPriceList() {
    Product product = oneProduct();
    List<Price> prices = List.of(onePrice());

    ProductPrices result = new ProductPrices(product, prices);

    assertEquals(product, result.product());
    assertEquals(prices, result.prices());
  }

  @Test
  void shouldThrowErrorWhenProductIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new ProductPrices(null, List.of(onePrice())));
  }

  @Test
  void shouldThrowErrorWhenPriceIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new ProductPrices(oneProduct(), null));
  }

  @Test
  void objectWithSameDataAreEquals() {
    ProductPrices oneProductPrices = oneProductPrices();
    ProductPrices sameProductPrices = oneProductPrices();

    assertEquals(oneProductPrices, sameProductPrices);
  }

  @Test
  void objectWithDifferentDataAreNotEquals() {
    ProductPrices oneProductPrices = oneProductPrices();
    ProductPrices otherProductPrices = oneProductNoPrices();

    assertNotEquals(oneProductPrices, otherProductPrices);
  }

}
