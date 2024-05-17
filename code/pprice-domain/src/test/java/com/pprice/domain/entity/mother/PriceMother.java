package com.pprice.domain.entity.mother;

import static com.pprice.domain.entity.price.Currency.EUR;

import java.time.Instant;
import java.time.OffsetDateTime;

import com.pprice.domain.entity.price.Currency;
import com.pprice.domain.entity.price.Price;

public class PriceMother {

  public static final Currency CURRENCY = EUR;

  public static final Double ONE_AMOUNT = 24.10;

  public static final Instant ONE_START_DATE = OffsetDateTime.parse("2020-06-13T21:00:00Z").toInstant();

  public static final Instant ONE_END_DATE = OffsetDateTime.parse("2020-06-16T20:00:00Z").toInstant();

  public static final Integer ONE_PRIORITY = 0;

  public static final Integer ONE_PRICE_ID = 1;

  public static Price onePrice() {
    return new Price(ONE_AMOUNT, CURRENCY, ONE_START_DATE, ONE_END_DATE, ONE_PRIORITY, ONE_PRICE_ID);
  }

  public static Price onePriceExpiration(Instant dateEnd) {
    return new Price(ONE_AMOUNT, CURRENCY, ONE_START_DATE, dateEnd, ONE_PRIORITY, ONE_PRICE_ID);
  }

}