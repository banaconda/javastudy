package com.cinapse.javastudy;

class VlanNotFoundException extends RuntimeException {

    VlanNotFoundException(Long id) {
        super("Could not find vlan " + id);
    }
}
