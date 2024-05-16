package com.pprice.domain.entity;

import static com.pprice.domain.entity.mother.ProductMother.ONE_BRAND_ID;
import static com.pprice.domain.entity.mother.ProductMother.ONE_PRODUCT_ID;
import static com.pprice.domain.entity.mother.ProductMother.oneProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.pprice.domain.exceptions.ValueValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductTest {

  @Test
  void shouldConstruct() {
    Integer productId = ONE_PRODUCT_ID;
    Integer brandId = ONE_BRAND_ID;

    Product result = new Product(productId, brandId);

    assertEquals(brandId, result.brandId());
    assertEquals(productId, result.productId());
  }

  @Test
  void shouldThrowErrorWhenProductIdIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new Product(null, ONE_BRAND_ID));
  }

  @Test
  void shouldThrowErrorWhenBrandIdIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new Product(ONE_PRODUCT_ID, null));
  }

  @Test
  void objectWithSameDataAreEquals() {
    Product oneProduct = oneProduct();
    Product sameProduct = oneProduct();

    assertEquals(oneProduct, sameProduct);
  }

  @Test
  void objectWithDifferentDataAreNotEquals() {
    Product oneProduct = oneProduct();
    Product otherProduct = oneProduct(85644);

    assertNotEquals(oneProduct, otherProduct);
  }

}
