package com.pprice.domain.entity.mother;

import com.pprice.domain.entity.Product;

public class ProductMother {

  private static final Integer ONE_PRODUCT_ID = 23145;

  private static final Integer ONE_BRAND_ID = 23145;

  public static Product oneProduct() {
    return new Product(ONE_PRODUCT_ID, ONE_BRAND_ID);
  }

}