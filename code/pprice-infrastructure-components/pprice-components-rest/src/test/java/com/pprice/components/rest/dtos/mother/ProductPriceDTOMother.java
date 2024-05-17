package com.pprice.components.rest.dtos.mother;

import java.time.OffsetDateTime;

import com.pprice.components.rest.dtos.PriceRestDTO;
import com.pprice.components.rest.dtos.ProductPriceRestDTO;

public class ProductPriceDTOMother {

  private static final Integer ONE_PRODUCT_ID = 23145;

  private static final Integer ONE_BRAND_ID = 1;

  private static final String CURRENCY = "EUR";

  private static final Double ONE_AMOUNT = 24.10;

  private static final OffsetDateTime ONE_START_DATE = OffsetDateTime.parse("2020-06-13T21:00:00Z");

  private static final OffsetDateTime ONE_END_DATE = OffsetDateTime.parse("2020-06-16T20:00:00Z");

  private static final Integer ONE_PRICE_ID = 1;

  public static ProductPriceRestDTO oneProductPriceDto() {
    return new ProductPriceRestDTO()
        .brandId(ONE_BRAND_ID)
        .productId(ONE_PRODUCT_ID)
        .startDate(ONE_START_DATE)
        .endDate(ONE_END_DATE)
        .rateId(ONE_PRICE_ID)
        .price(onePriceDto());
  }

  public static PriceRestDTO onePriceDto() {
    return new PriceRestDTO()
        .amount(ONE_AMOUNT)
        .currency(CURRENCY);
  }

}
