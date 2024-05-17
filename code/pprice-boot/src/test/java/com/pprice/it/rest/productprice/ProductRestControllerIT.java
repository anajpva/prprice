package com.pprice.it.rest.productprice;

import static java.util.Objects.requireNonNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.time.OffsetDateTime;

import com.pprice.components.rest.controllers.ProductRestController;
import com.pprice.components.rest.dtos.ProductPriceRestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductRestControllerIT {

  Integer PRODUCT_ID = 35455;

  Integer BRAND_ID = 1;

  @Autowired
  private ProductRestController productRestController;

  @Test
  void shouldReturnPrice1ForProductAndDate20200614T100000Z() {
    OffsetDateTime date = OffsetDateTime.parse("2020-06-14T10:00:00Z");

    ResponseEntity<ProductPriceRestDTO> response = productRestController.getProductPrice(BRAND_ID, PRODUCT_ID, date);

    assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    assertEquals(1, requireNonNull(response.getBody()).getRateId());
  }

  @Test
  void shouldReturnPrice1ForProductAndDate20200614T160000Z() {
    OffsetDateTime date = OffsetDateTime.parse("2020-06-14T16:00:00Z");

    ResponseEntity<ProductPriceRestDTO> response = productRestController.getProductPrice(BRAND_ID, PRODUCT_ID, date);

    assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    assertEquals(2, requireNonNull(response.getBody()).getRateId());
  }

  @Test
  void shouldReturnPrice1ForProductAndDate20200614T210000Z() {
    OffsetDateTime date = OffsetDateTime.parse("2020-06-14T21:00:00Z");

    ResponseEntity<ProductPriceRestDTO> response = productRestController.getProductPrice(BRAND_ID, PRODUCT_ID, date);

    assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    assertEquals(1, requireNonNull(response.getBody()).getRateId());
  }

  @Test
  void shouldReturnPrice1ForProductAndDate20200615T100000Z() {
    OffsetDateTime date = OffsetDateTime.parse("2020-06-15T10:00:00Z");

    ResponseEntity<ProductPriceRestDTO> response = productRestController.getProductPrice(BRAND_ID, PRODUCT_ID, date);

    assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    assertEquals(3, requireNonNull(response.getBody()).getRateId());
  }

  @Test
  void shouldReturnPrice1ForProductAndDate20200616T121000Z() {
    OffsetDateTime date = OffsetDateTime.parse("2020-06-16T21:00:00Z");

    ResponseEntity<ProductPriceRestDTO> response = productRestController.getProductPrice(BRAND_ID, PRODUCT_ID, date);

    assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    assertEquals(4, requireNonNull(response.getBody()).getRateId());
  }

}
