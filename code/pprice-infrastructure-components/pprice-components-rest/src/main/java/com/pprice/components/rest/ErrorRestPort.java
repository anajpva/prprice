package com.pprice.components.rest;

import com.pprice.components.rest.dtos.ErrorDTO;
import org.springframework.http.ResponseEntity;

public interface ErrorRestPort {

  ResponseEntity<ErrorDTO> onHandleError(Exception cause);

}
