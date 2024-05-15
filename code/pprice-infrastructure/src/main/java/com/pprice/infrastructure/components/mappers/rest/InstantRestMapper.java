package com.pprice.infrastructure.components.mappers.rest;

import static java.time.ZoneOffset.UTC;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Optional;

import org.mapstruct.Mapper;

@Mapper
public interface InstantRestMapper {

  default OffsetDateTime toDTO(Instant date) {
    return Optional.ofNullable(date)
        .map(nonNullDate -> nonNullDate.atOffset(UTC))
        .orElse(null);
  }

  default Instant toEntity(OffsetDateTime offsetDateTime) {
    return Optional.ofNullable(offsetDateTime)
        .map(OffsetDateTime::toInstant)
        .orElse(null);
  }
}
