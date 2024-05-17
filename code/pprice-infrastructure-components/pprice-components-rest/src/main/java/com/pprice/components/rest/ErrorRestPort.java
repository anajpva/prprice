package com.pprice.components.rest;

import com.pprice.components.rest.dtos.ErrorRestDTO;
import org.springframework.http.ResponseEntity;

public interface ErrorRestPort {

  ResponseEntity<ErrorRestDTO> onHandleError(Exception cause);

}
