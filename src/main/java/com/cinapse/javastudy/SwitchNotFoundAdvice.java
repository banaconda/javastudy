package com.cinapse.javastudy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class SwitchNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SwitchNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String switchNotFoundHandler(SwitchNotFoundException ex) {
        return ex.getMessage();
    }
}
