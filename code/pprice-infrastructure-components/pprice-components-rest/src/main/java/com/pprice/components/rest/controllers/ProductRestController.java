package com.pprice.components.rest.controllers;

import java.time.OffsetDateTime;

import com.pprice.components.rest.ProductRestPort;
import com.pprice.components.rest.dtos.ProductPriceRestDTO;
import com.pprice.components.rest.services.V1Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductRestController implements V1Api {

  private final ProductRestPort productRestPort;

  @Override
  public ResponseEntity<ProductPriceRestDTO> getProductPrice(Integer brandId, Integer productId, OffsetDateTime date) {
    ProductPriceRestDTO productPriceDto = productRestPort.onGetProductPrice(brandId, productId, date);
    return ResponseEntity.ok(productPriceDto);
  }

}
