package com.pprice.infrastructure.components.primary.rest;

import java.time.Instant;
import java.time.OffsetDateTime;

import com.pprice.application.usecases.prices.GetProductPrice;
import com.pprice.application.usecases.prices.params.GetProductPriceParams;
import com.pprice.components.rest.ProductRestPort;
import com.pprice.components.rest.dtos.ProductPriceDTO;
import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrice;
import com.pprice.infrastructure.components.mappers.rest.InstantRestMapper;
import com.pprice.infrastructure.components.mappers.rest.ProductPriceRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRestAdapter implements ProductRestPort {

  private final GetProductPrice getProductPrice;

  private final InstantRestMapper instantRestMapper;

  private final ProductPriceRestMapper productPriceRestMapper;

  @Override
  public ProductPriceDTO onGetProductPrice(
      Integer brandIdRestDto,
      Integer productIdRestDto,
      OffsetDateTime dateRestDto) {

    Product product = new Product(productIdRestDto, brandIdRestDto);
    Instant date = instantRestMapper.toEntity(dateRestDto);

    GetProductPriceParams params = new GetProductPriceParams(product, date);
    ProductPrice productPrice = getProductPrice.execute(params);

    return productPriceRestMapper.toDTO(productPrice);
  }

}
