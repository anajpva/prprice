package com.pprice.infrastructure.components.mappers.rest;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

import com.pprice.components.rest.dtos.ProductPriceRestDTO;
import com.pprice.domain.entity.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    injectionStrategy = CONSTRUCTOR,
    uses = {
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
  ProductPriceRestDTO toDTO(ProductPrice entity);
}
