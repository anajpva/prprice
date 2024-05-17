package com.pprice.infrastructure.components.primary.rest;

import com.pprice.components.rest.ErrorRestPort;
import com.pprice.components.rest.dtos.ErrorRestDTO;
import com.pprice.components.rest.dtos.ErrorRestDTO.CodeEnum;
import com.pprice.domain.exceptions.ProductNotFoundException;
import com.pprice.domain.exceptions.ProductPriceNotFoundException;
import com.pprice.domain.exceptions.ValueValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.server.ServerWebInputException;

@Component
class ErrorRestAdapter implements ErrorRestPort {

  @Override
  public ResponseEntity<ErrorRestDTO> onHandleError(Exception cause) {
    CodeEnum code = CodeEnum.INTERNAL_ERROR;
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    if (cause instanceof ProductPriceNotFoundException) {
      code = CodeEnum.PRODUCT_PRICE_NOT_FOUND;
      status = HttpStatus.NOT_FOUND;
    } else if (cause instanceof ProductNotFoundException) {
      code = CodeEnum.PRODUCT_NOT_FOUND;
      status = HttpStatus.NOT_FOUND;
    } else if (cause instanceof ValueValidationException
        || cause instanceof MissingServletRequestParameterException
        || cause instanceof ServerWebInputException) {
      code = CodeEnum.BAD_REQUEST;
      status = HttpStatus.BAD_REQUEST;
    }

    ErrorRestDTO errorDTO = new ErrorRestDTO()
        .code(code)
        .message(cause.getMessage());

    return ResponseEntity.status(status)
        .body(errorDTO);
  }
}
