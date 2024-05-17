package com.pprice.components.rest.handlers;

import com.pprice.components.rest.ErrorRestPort;
import com.pprice.components.rest.dtos.ErrorRestDTO;
import com.pprice.components.rest.dtos.ErrorRestDTO.CodeEnum;
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
  public ResponseEntity<ErrorRestDTO> handleUnauthorizedException(IllegalArgumentException cause) {
    ErrorRestDTO errorDTO = new ErrorRestDTO()
        .code(CodeEnum.BAD_REQUEST)
        .message(cause.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(errorDTO);
  }

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorRestDTO> handleException(Exception cause) {
    return errorRestPort.onHandleError(cause);
  }

}


