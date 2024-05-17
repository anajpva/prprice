package com.pprice.components.rest;

import java.time.OffsetDateTime;

import com.pprice.components.rest.dtos.ProductPriceRestDTO;

public interface ProductRestPort {

  ProductPriceRestDTO onGetProductPrice(
      Integer brandId,
      Integer productId,
      OffsetDateTime date);
}
