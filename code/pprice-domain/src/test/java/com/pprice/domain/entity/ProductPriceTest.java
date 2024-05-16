package com.pprice.domain.entity;

import static com.pprice.domain.entity.mother.PriceMother.onePrice;
import static com.pprice.domain.entity.mother.ProductMother.oneProduct;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.pprice.domain.entity.price.Price;
import com.pprice.domain.exceptions.ValueValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductPriceTest {

  @Test
  void shouldConstruct() {
    Product product = oneProduct();
    Price price = onePrice();

    ProductPrice result = new ProductPrice(product, price);

    assertEquals(product, result.product());
    assertEquals(price, result.price());
  }

  @Test
  void shouldThrowErrorWhenProductIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new ProductPrice(null, onePrice()));
  }

  @Test
  void shouldThrowErrorWhenPriceIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new ProductPrice(oneProduct(), null));
  }

  @Test
  void objectWithSameDataAreEquals() {
    ProductPrice oneProductPrice = oneProductPrice();
    ProductPrice sameProductPrice = oneProductPrice();

    assertEquals(oneProductPrice, sameProductPrice);
  }

  @Test
  void objectWithDifferentDataAreNotEquals() {
    ProductPrice oneProductPrice = oneProductPrice();
    ProductPrice otherProductPrice = oneProductPrice(oneProduct(85644));

    assertNotEquals(oneProductPrice, otherProductPrice);
  }

}
