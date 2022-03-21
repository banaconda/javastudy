package com.cinapse.javastudy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class VlanNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(VlanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String switchNotFoundHandler(VlanNotFoundException ex) {
        return ex.getMessage();
    }
}
