package com.cinapse.javastudy;

class SwitchNotFoundException extends RuntimeException {

    SwitchNotFoundException(Long id) {
        super("Could not find switch " + id);
    }
}
