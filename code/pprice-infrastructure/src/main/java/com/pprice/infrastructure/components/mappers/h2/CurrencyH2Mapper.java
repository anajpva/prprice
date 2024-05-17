package com.pprice.infrastructure.components.mappers.h2;

import java.util.Optional;

import com.pprice.domain.entity.price.Currency;
import org.mapstruct.Mapper;

@Mapper
public interface CurrencyH2Mapper {

  default Currency toEntity(String dto) {
    return Optional.ofNullable(dto).map(Currency::valueOf).orElse(null);
  }

}
