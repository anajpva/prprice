package com.pprice.domain.entity.price;

import java.time.Instant;

import com.pprice.domain.exceptions.ValueValidationException;

public record Price(Double amount, Currency currency, Instant startDate, Instant endDate, Integer priority, Integer priceId) {

  public Price {

    if (amount == null) {
      throw new ValueValidationException("Amount cannot be null");
    }

    if (currency == null) {
      throw new ValueValidationException("Currency cannot be null");
    }

    if (startDate == null) {
      throw new ValueValidationException("Start date cannot be null");
    }

    if (endDate == null || startDate.isAfter(endDate)) {
      throw new ValueValidationException("End date cannot be null or lower than start date");
    }

    if (priority == null) {
      throw new ValueValidationException("Priority cannot be null");
    }

    if (priceId == null) {
      throw new ValueValidationException("Price id cannot be null");
    }

  }

}
