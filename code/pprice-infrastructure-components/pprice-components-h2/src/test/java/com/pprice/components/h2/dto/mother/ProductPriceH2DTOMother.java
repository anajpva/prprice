package com.pprice.components.h2.dto.mother;

import java.time.OffsetDateTime;

import com.pprice.components.h2.dto.ProductPriceH2DTO;

public class ProductPriceH2DTOMother {

  private static final Integer ONE_PRODUCT_ID = 23145;

  private static final Integer ONE_BRAND_ID = 1;

  private static final String CURRENCY = "EUR";

  private static final Double ONE_AMOUNT = 24.10;

  private static final OffsetDateTime ONE_START_DATE = OffsetDateTime.parse("2020-06-13T21:00:00Z");

  private static final OffsetDateTime ONE_END_DATE = OffsetDateTime.parse("2020-06-16T20:00:00Z");

  private static final Integer ONE_PRIORITY = 0;

  private static final Integer ONE_PRICE_ID = 1;

  public static ProductPriceH2DTO oneProductPriceH2Dto() {
    return new ProductPriceH2DTO(
        ONE_PRODUCT_ID,
        ONE_BRAND_ID,
        ONE_START_DATE,
        ONE_END_DATE,
        ONE_PRIORITY,
        ONE_PRICE_ID,
        ONE_AMOUNT,
        CURRENCY
    );
  }

}
