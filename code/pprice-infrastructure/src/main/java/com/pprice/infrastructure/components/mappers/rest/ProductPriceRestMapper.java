package com.pprice.infrastructure.components.mappers.rest;

import com.pprice.components.rest.dtos.ProductPriceDTO;
import com.pprice.domain.entity.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {
    InstantRestMapper.class,
    CurrencyRestMapper.class
})
public interface ProductPriceRestMapper {

  @Mapping(source = "entity.product.productId", target = "productId")
  @Mapping(source = "entity.product.brandId", target = "brandId")
  @Mapping(source = "entity.price.priceId", target = "rateId")
  @Mapping(source = "entity.price.startDate", target = "startDate")
  @Mapping(source = "entity.price.endDate", target = "endDate")
  @Mapping(source = "entity.price.amount", target = "price.amount")
  @Mapping(source = "entity.price.currency", target = "price.currency")
  ProductPriceDTO toDTO(ProductPrice entity);
}
