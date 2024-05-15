package com.pprice.infrastructure.components.mappers.h2;

import java.util.Collection;
import java.util.List;

import com.pprice.components.h2.dto.ProductPriceH2DTO;
import com.pprice.domain.entity.price.Price;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {
    InstantH2Mapper.class,
    CurrencyH2Mapper.class
})
public interface PriceH2Mapper {

  @Mapping(source = "curr", target = "currency")
  @Mapping(source = "price", target = "amount")
  @Mapping(source = "priceList", target = "priceId")
  Price toEntity(ProductPriceH2DTO dto);

  List<Price> toEntities(Collection<ProductPriceH2DTO> dtos);

}
