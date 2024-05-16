package com.pprice.domain.entity.mother;

import com.pprice.domain.entity.Product;

public class ProductMother {

  public static final Integer ONE_PRODUCT_ID = 23145;

  public static final Integer ONE_BRAND_ID = 1;

  public static Product oneProduct(Integer productId) {
    return new Product(productId, ONE_BRAND_ID);
  }

  public static Product oneProduct() {
    return oneProduct(ONE_PRODUCT_ID);
  }

}