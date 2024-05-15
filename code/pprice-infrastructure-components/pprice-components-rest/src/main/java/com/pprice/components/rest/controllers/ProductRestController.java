package com.pprice.components.rest.controllers;

import com.pprice.components.rest.ProductRestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.pprice.components.rest.dtos.ProductPriceDTO;
import com.pprice.components.rest.services.V1Api;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class ProductRestController implements V1Api {

  private final ProductRestPort productRestPort;

  @Override
  public ResponseEntity<ProductPriceDTO> getProductPrice(Integer brandId, Integer productId, OffsetDateTime date) {
    ProductPriceDTO productPriceDto = productRestPort.onGetProductPrice(brandId, productId, date);
    return ResponseEntity.ok(productPriceDto);
  }

}
