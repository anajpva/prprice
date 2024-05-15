package com.pprice.infrastructure.components.primary.rest;

import com.pprice.components.rest.dtos.ErrorDTO;
import com.pprice.components.rest.dtos.ErrorDTO.CodeEnum;
import com.pprice.components.rest.ErrorRestPort;
import com.pprice.domain.exceptions.ProductNotFoundException;
import com.pprice.domain.exceptions.ProductPriceNotFoundException;
import com.pprice.domain.exceptions.ValueValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebInputException;

@Component
class ErrorRestAdapter implements ErrorRestPort {

  @Override
  public ResponseEntity<ErrorDTO> onHandleError(Exception cause) {
    CodeEnum code = CodeEnum.INTERNAL_ERROR;
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    if (cause instanceof ProductPriceNotFoundException) {
      code = CodeEnum.PRODUCT_PRICE_NOT_FOUND;
      status = HttpStatus.NOT_FOUND;
    } else if (cause instanceof ProductNotFoundException) {
      code = CodeEnum.PRODUCT_NOT_FOUND;
      status = HttpStatus.NOT_FOUND;
    } else if (cause instanceof ValueValidationException
        || cause instanceof ServerWebInputException) {
      code = CodeEnum.BAD_REQUEST;
      status = HttpStatus.BAD_REQUEST;
    }

    ErrorDTO errorDTO = new ErrorDTO()
        .code(code)
        .message(cause.getMessage());

    return ResponseEntity.status(status)
        .body(errorDTO);
  }
}
