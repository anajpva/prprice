package com.pprice.domain.entity.price;

import static java.time.Instant.now;

import static com.pprice.domain.entity.mother.PriceMother.CURRENCY;
import static com.pprice.domain.entity.mother.PriceMother.ONE_AMOUNT;
import static com.pprice.domain.entity.mother.PriceMother.ONE_END_DATE;
import static com.pprice.domain.entity.mother.PriceMother.ONE_PRICE_ID;
import static com.pprice.domain.entity.mother.PriceMother.ONE_PRIORITY;
import static com.pprice.domain.entity.mother.PriceMother.ONE_START_DATE;
import static com.pprice.domain.entity.mother.PriceMother.onePrice;
import static com.pprice.domain.entity.mother.PriceMother.onePriceExpiration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import com.pprice.domain.exceptions.ValueValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceTest {

  @Test
  void shouldConstruct() {
    Double amount = ONE_AMOUNT;
    Currency currency = CURRENCY;
    Instant startDate = ONE_START_DATE;
    Instant endDate = ONE_END_DATE;
    Integer priority = ONE_PRIORITY;
    Integer priceId = ONE_PRICE_ID;

    Price result = new Price(amount,
        currency,
        startDate,
        endDate,
        priority,
        priceId
    );

    assertEquals(amount, result.amount());
    assertEquals(currency, result.currency());
    assertEquals(startDate, result.startDate());
    assertEquals(endDate, result.endDate());
    assertEquals(priority, result.priority());
    assertEquals(priceId, result.priceId());
  }

  @Test
  void shouldThrowErrorWhenAmountIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new Price(null,
            CURRENCY,
            ONE_START_DATE,
            ONE_END_DATE,
            ONE_PRIORITY,
            ONE_PRICE_ID
        ));
  }

  @Test
  void shouldThrowErrorWhenCurrencyIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new Price(ONE_AMOUNT,
            null,
            ONE_START_DATE,
            ONE_END_DATE,
            ONE_PRIORITY,
            ONE_PRICE_ID
        ));
  }

  @Test
  void shouldThrowErrorWhenStartDateIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new Price(ONE_AMOUNT,
            CURRENCY,
            null,
            ONE_END_DATE,
            ONE_PRIORITY,
            ONE_PRICE_ID
        ));
  }

  @Test
  void shouldThrowErrorWhenEndDateIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new Price(ONE_AMOUNT,
            CURRENCY,
            ONE_START_DATE,
            null,
            ONE_PRIORITY,
            ONE_PRICE_ID
        ));
  }

  @Test
  void shouldThrowErrorWhenEndDateBeforeStartDate() {
    assertThrows(ValueValidationException.class,
        () -> new Price(ONE_AMOUNT,
            CURRENCY,
            ONE_END_DATE,
            ONE_START_DATE,
            ONE_PRIORITY,
            ONE_PRICE_ID
        ));
  }

  @Test
  void shouldThrowErrorWhenPriorityIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new Price(ONE_AMOUNT,
            CURRENCY,
            ONE_START_DATE,
            ONE_END_DATE,
            null,
            ONE_PRICE_ID
        ));
  }

  @Test
  void shouldThrowErrorWhenPriceIdIsNull() {
    assertThrows(ValueValidationException.class,
        () -> new Price(ONE_AMOUNT,
            CURRENCY,
            ONE_START_DATE,
            ONE_END_DATE,
            ONE_PRIORITY,
            null
        ));
  }

  @Test
  void objectWithSameDataAreEquals() {
    Price onePrice = onePrice();
    Price samePrice = onePrice();

    assertEquals(onePrice, samePrice);
  }

  @Test
  void objectWithDifferentDataAreNotEquals() {
    Price onePrice = onePrice();
    Price otherPrice = onePriceExpiration(now());

    assertNotEquals(onePrice, otherPrice);
  }

}
