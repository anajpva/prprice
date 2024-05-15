package com.pprice.infrastructure.components.mappers.rest;

import java.util.Optional;

import com.pprice.domain.entity.price.Currency;

import org.mapstruct.Mapper;

@Mapper
public interface CurrencyRestMapper {

  default String toDTO(Currency entity) {
    return Optional.ofNullable(entity).map(Currency::name).orElse(null);
  }

}
