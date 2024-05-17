package com.pprice.components.rest;

import java.time.OffsetDateTime;

import com.pprice.components.rest.dtos.ProductPriceDTO;

public interface ProductRestPort {

  ProductPriceDTO onGetProductPrice(
      Integer brandId,
      Integer productId,
      OffsetDateTime date);
}
