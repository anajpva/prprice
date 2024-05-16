package com.pprice.infrastructure.components.primary.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pprice.components.rest.dtos.ErrorDTO;
import com.pprice.components.rest.dtos.ErrorDTO.CodeEnum;
import com.pprice.domain.exceptions.ProductNotFoundException;
import com.pprice.domain.exceptions.ProductPriceNotFoundException;
import com.pprice.domain.exceptions.ValueValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.server.ServerWebInputException;

@ExtendWith(MockitoExtension.class)
class ErrorRestAdapterTest {

  @InjectMocks
  private ErrorRestAdapter errorRestAdapter;

  @Test
  void shouldReturnANotFoundCodeWhereThereIsAProductNotFoundException() {
    ProductNotFoundException exception = new ProductNotFoundException("Product not found");

    ResponseEntity<ErrorDTO> result = errorRestAdapter.onHandleError(exception);

    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(CodeEnum.PRODUCT_NOT_FOUND, result.getBody().getCode());
    assertEquals(exception.getMessage(), result.getBody().getMessage());
  }

  @Test
  void shouldReturnANotFoundCodeWhereThereIsAProductPriceNotFoundException() {
    ProductPriceNotFoundException exception = new ProductPriceNotFoundException("Product price not found");

    ResponseEntity<ErrorDTO> result = errorRestAdapter.onHandleError(exception);

    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(CodeEnum.PRODUCT_PRICE_NOT_FOUND, result.getBody().getCode());
    assertEquals(exception.getMessage(), result.getBody().getMessage());
  }

  @Test
  void shouldReturnABadRequestCodeWhereThereIsAValueValidationException() {
    ValueValidationException exception = new ValueValidationException("Value can not be null");

    ResponseEntity<ErrorDTO> result = errorRestAdapter.onHandleError(exception);

    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(CodeEnum.BAD_REQUEST, result.getBody().getCode());
    assertEquals(exception.getMessage(), result.getBody().getMessage());
  }

  @Test
  void shouldReturnABadRequestCodeWhereThereIsAServerWebInputException() {
    ServerWebInputException exception = new ServerWebInputException("Server web input exception");

    ResponseEntity<ErrorDTO> result = errorRestAdapter.onHandleError(exception);

    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(CodeEnum.BAD_REQUEST, result.getBody().getCode());
    assertEquals(exception.getMessage(), result.getBody().getMessage());
  }

  @Test
  void shouldReturnABadRequestCodeWhereThereIsAMissingServletRequestParameterException() {
    MissingServletRequestParameterException exception = new MissingServletRequestParameterException("attribute", "long");

    ResponseEntity<ErrorDTO> result = errorRestAdapter.onHandleError(exception);

    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(CodeEnum.BAD_REQUEST, result.getBody().getCode());
    assertEquals(exception.getMessage(), result.getBody().getMessage());
  }

  @Test
  void shouldReturnAInternalErrorCodeWhereThereIsOtherException() {
    RuntimeException exception = new RuntimeException();

    ResponseEntity<ErrorDTO> result = errorRestAdapter.onHandleError(exception);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    assertNotNull(result.getBody());
    assertEquals(CodeEnum.INTERNAL_ERROR, result.getBody().getCode());
    assertEquals(exception.getMessage(), result.getBody().getMessage());
  }

}
