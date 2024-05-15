package com.pprice.infrastructure.components.mappers.h2;

import static com.google.common.base.Objects.equal;

import java.util.Collection;
import java.util.List;

import com.pprice.components.h2.dto.ProductPriceH2DTO;
import com.pprice.domain.entity.price.Price;
import com.pprice.domain.entity.Product;
import com.pprice.domain.entity.ProductPrices;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ProductPriceH2Mapper {

  @Autowired
  private PriceH2Mapper priceH2Mapper;

  @Autowired
  private ProductH2Mapper productH2Mapper;

  public ProductPrices toEntity(Collection<ProductPriceH2DTO> dtos, Integer productId, Integer brandId) {
    List<ProductPriceH2DTO> productPriceH2Dtos = dtos.stream()
        .filter(productPriceH2Dto -> equal(productPriceH2Dto.getProductId(), productId) && equal(productPriceH2Dto.getBrandId(), brandId))
        .toList();

    Product product = productH2Mapper.toEntity(productId, brandId);
    List<Price> prices = priceH2Mapper.toEntities(productPriceH2Dtos);

    return new ProductPrices(product, prices);
  }

}
