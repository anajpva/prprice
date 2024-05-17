package com.pprice.components.rest.handlers;

import static com.pprice.components.rest.dtos.ErrorRestDTO.CodeEnum.INTERNAL_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.pprice.components.rest.ErrorRestPort;
import com.pprice.components.rest.dtos.ErrorRestDTO;
import com.pprice.components.rest.dtos.ErrorRestDTO.CodeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class RestExceptionsHandlerTest {

  @Mock
  private ErrorRestPort errorRestPort;

  @InjectMocks
  private RestExceptionsHandler restExceptionsHandler;

  @Test
  void shouldReturnBadRequestOnIllegalArgumentException() {
    IllegalArgumentException cause = new IllegalArgumentException("Invalid parameter");

    ResponseEntity<ErrorRestDTO> response = restExceptionsHandler.handleUnauthorizedException(cause);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(CodeEnum.BAD_REQUEST, response.getBody().getCode());
    assertEquals(cause.getMessage(), response.getBody().getMessage());
  }

  @Test
  void shouldCallPortOnException() {
    RuntimeException cause = new RuntimeException("Runtime error message");
    ErrorRestDTO errorDto = new ErrorRestDTO()
        .code(INTERNAL_ERROR)
        .message(cause.getMessage());
    ResponseEntity<ErrorRestDTO> internalErrorRestDTO = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);

    when(errorRestPort.onHandleError(cause))
        .thenReturn(internalErrorRestDTO);

    ResponseEntity<ErrorRestDTO> response = restExceptionsHandler.handleException(cause);

    assertEquals(internalErrorRestDTO, response);
  }

}
