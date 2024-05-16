package com.pprice.infrastructure.components.primary.rest;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.HOURS;

import static com.pprice.components.rest.dtos.mother.ProductPriceDTOMother.oneProductPriceDto;
import static com.pprice.domain.entity.mother.ProductPricesMother.oneProductPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.OffsetDateTime;

import com.pprice.application.usecases.prices.GetProductPrice;
import com.pprice.application.usecases.prices.params.GetProductPriceParams;
import com.pprice.components.rest.dtos.ProductPriceDTO;
import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrice;
import com.pprice.domain.entity.price.Price;
import com.pprice.infrastructure.components.mappers.rest.CurrencyRestMapperImpl;
import com.pprice.infrastructure.components.mappers.rest.InstantRestMapper;
import com.pprice.infrastructure.components.mappers.rest.InstantRestMapperImpl;
import com.pprice.infrastructure.components.mappers.rest.ProductPriceRestMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductRestAdapterTest {

  @Captor
  private ArgumentCaptor<GetProductPriceParams> getProductPriceParamsArgumentCaptor;

  @Mock
  private GetProductPrice getProductPrice;

  @Spy
  private InstantRestMapper instantRestMapper;

  @Spy
  private final ProductPriceRestMapperImpl productPriceRestMapper =
      new ProductPriceRestMapperImpl(new InstantRestMapperImpl(), new CurrencyRestMapperImpl());

  @InjectMocks
  private ProductRestAdapter productRestAdapter;

  @Test
  void shouldCallUseCaseToGetProductPrice() {
    ProductPrice productPrice = oneProductPrice();
    Product expectedProduct = productPrice.product();
    Price price = productPrice.price();
    OffsetDateTime date = price.startDate().plus(1, HOURS).atOffset(UTC);
    Instant expectedInstant = date.toInstant();

    when(getProductPrice.execute(getProductPriceParamsArgumentCaptor.capture()))
        .thenReturn(productPrice);

    ProductPriceDTO result = productRestAdapter.onGetProductPrice(expectedProduct.brandId(), expectedProduct.productId(), date);

    assertEquals(oneProductPriceDto(), result);
    assertEquals(expectedProduct, getProductPriceParamsArgumentCaptor.getValue().getProduct());
    assertEquals(expectedInstant, getProductPriceParamsArgumentCaptor.getValue().getDate());
  }

}
