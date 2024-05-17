package com.pprice.domain.services.price;

import static java.time.Instant.now;

import static com.pprice.domain.entity.mother.PriceMother.onePrice;
import static com.pprice.domain.entity.mother.PriceMother.onePriceExpiration;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.pprice.domain.entity.price.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceConsolidatorTest {

  @InjectMocks
  private PriceConsolidator priceConsolidator;

  @Test
  void shouldReturnPriorityPrice() {
    Price zeroPriorityPrice = onePrice(0);
    Price onePriorityPrice = onePrice(1);
    List<Price> prices = List.of(zeroPriorityPrice, onePriorityPrice);

    List<Price> result = priceConsolidator.consolidate(prices);

    assertEquals(List.of(onePriorityPrice), result);
  }

  @Test
  void shouldReturnPricesOverlapped() {
    Price firstPrice = onePrice(1);
    Price secondPrice = onePriceExpiration(now());
    List<Price> prices = List.of(secondPrice, firstPrice);

    List<Price> result = priceConsolidator.consolidate(prices);

    assertEquals(2, result.size());
    assertEquals(firstPrice, result.getFirst());
    assertEquals(firstPrice.endDate(), result.getLast().startDate());
    assertEquals(secondPrice.endDate(), result.getLast().endDate());
    assertEquals(secondPrice.priority(), result.getLast().priority());
  }

}