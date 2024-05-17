package com.pprice.infrastructure.components.mappers.h2;

import com.pprice.domain.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductH2Mapper {

  Product toEntity(Integer productId, Integer brandId);

}
