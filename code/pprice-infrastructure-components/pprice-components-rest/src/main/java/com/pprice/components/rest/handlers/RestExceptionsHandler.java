package com.pprice.components.rest.handlers;

import com.pprice.components.rest.ErrorRestPort;
import com.pprice.components.rest.dtos.ErrorDTO;
import com.pprice.components.rest.dtos.ErrorDTO.CodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
class RestExceptionsHandler {

  private final ErrorRestPort errorRestPort;

  @ResponseBody
  @ExceptionHandler(value = IllegalArgumentException.class)
  public ResponseEntity<ErrorDTO> handleUnauthorizedException(IllegalArgumentException cause) {
    ErrorDTO errorDTO = new ErrorDTO()
        .code(CodeEnum.BAD_REQUEST)
        .message(cause.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(errorDTO);
  }

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorDTO> handleException(Exception cause) {
    return errorRestPort.onHandleError(cause);
  }

}


