package com.pprice.components.rest.controllers;

import static com.pprice.components.rest.dtos.mother.ProductPriceDTOMother.oneProductPriceDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;

import com.pprice.components.rest.ProductRestPort;
import com.pprice.components.rest.dtos.ProductPriceRestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProductRestControllerTest {

  @Mock
  private ProductRestPort productRestPort;

  @InjectMocks
  private ProductRestController productRestController;

  @Test
  void shouldCallPortOnGetProductPrice() {
    ProductPriceRestDTO productPriceDto = oneProductPriceDto();
    Integer brandId = productPriceDto.getBrandId();
    Integer productId = productPriceDto.getProductId();
    OffsetDateTime date = productPriceDto.getStartDate();

    when(productRestPort.onGetProductPrice(brandId, productId, date))
        .thenReturn(productPriceDto);

    ResponseEntity<ProductPriceRestDTO> result =
        productRestController.getProductPrice(brandId, productId, date);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(productPriceDto, result.getBody());
  }

}
