package com.pprice.domain.services.price;

import static java.util.Optional.ofNullable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.pprice.domain.entity.price.Price;
import com.pprice.domain.services.DomainService;

@DomainService
public class PriceConsolidator {

  public List<Price> consolidate(Collection<Price> prices) {
    Stream<Instant> startDates = prices.stream().map(Price::startDate);
    Stream<Instant> endDates = prices.stream().map(Price::endDate);
    List<Instant> dates = Stream.concat(startDates, endDates).distinct().sorted().toList();

    List<Price> consolidatedPrices = new ArrayList<>();

    for (int i = 0; i < dates.size() - 1; i++) {
      Instant currentStartDate = dates.get(i);
      Instant currentEndDate = dates.get(i + 1);

      Optional<Price> currentPrice = getPriorityPriceForDate(prices, currentStartDate);

      currentPrice.ifPresent(price ->
          consolidatedPrices.add(new Price(price, currentStartDate, currentEndDate))
      );
    }

    return consolidatedPrices;
  }

  private Optional<Price> getPriorityPriceForDate(Collection<Price> prices, Instant date) {
    List<Price> datePrices = prices.stream()
        .filter(datePrice -> date.isBefore(datePrice.endDate()) && !date.isBefore(datePrice.startDate()))
        .sorted(Comparator.comparingInt(Price::priority).reversed())
        .toList();

    return ofNullable(datePrices.getFirst());
  }

}
