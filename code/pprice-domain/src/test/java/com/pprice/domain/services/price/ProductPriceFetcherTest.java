package com.pprice.domain.services.price;

import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductNoPrices;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductPrices;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrices;
import com.pprice.domain.exceptions.ProductNotFoundException;
import com.pprice.domain.repositories.ProductPricesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductPriceFetcherTest {

  @Spy
  private PriceConsolidator priceConsolidator;

  @Mock
  private ProductPricesRepository productPricesRepository;

  @InjectMocks
  private ProductPriceFetcher productPriceFetcher;

  @Test
  void shouldThrowProductNotFoundExceptionWhenNoPrices() {
    ProductPrices productPrices = oneProductNoPrices();
    Product product = productPrices.product();

    when(productPricesRepository.findByProduct(product))
        .thenReturn(productPrices);

    assertThrows(ProductNotFoundException.class,
        () -> productPriceFetcher.fetch(product));
  }

  @Test
  void shouldReturnProductPrice() {
    ProductPrices productPrices = oneProductPrices();
    Product product = productPrices.product();

    when(productPricesRepository.findByProduct(product))
        .thenReturn(productPrices);

    ProductPrices result = productPriceFetcher.fetch(product);

    assertEquals(productPrices, result);
  }

}
