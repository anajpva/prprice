package com.pprice.application.usecases.prices.mother;

import static com.pprice.domain.entity.mother.ProductMother.oneProduct;

import java.time.Instant;
import java.time.OffsetDateTime;

import com.pprice.application.usecases.prices.params.GetProductPriceParams;

public class GetProductPriceParamsMother {

  private static final Instant DATE = OffsetDateTime.parse("2020-06-14T21:00:00Z").toInstant();

  public static GetProductPriceParams oneGetProductPriceParams() {
    return new GetProductPriceParams(oneProduct(), DATE);
  }

  public static GetProductPriceParams oneGetProductPriceParamsNoDate() {
    return new GetProductPriceParams(oneProduct(), null);
  }
}
