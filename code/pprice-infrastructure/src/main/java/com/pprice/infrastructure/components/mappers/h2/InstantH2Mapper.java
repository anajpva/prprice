package com.pprice.infrastructure.components.mappers.h2;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Optional;

import org.mapstruct.Mapper;

@Mapper
public interface InstantH2Mapper {

  default Instant toEntity(OffsetDateTime offsetDateTime) {
    return Optional.ofNullable(offsetDateTime)
        .map(OffsetDateTime::toInstant)
        .orElse(null);
  }
}
