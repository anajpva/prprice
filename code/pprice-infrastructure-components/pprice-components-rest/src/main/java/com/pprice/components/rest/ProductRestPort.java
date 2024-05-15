package com.pprice.components.rest;

import com.pprice.components.rest.dtos.ProductPriceDTO;

import java.time.OffsetDateTime;

public interface ProductRestPort {

  ProductPriceDTO onGetProductPrice(
      Integer brandId,
      Integer productId,
      OffsetDateTime date);
}
