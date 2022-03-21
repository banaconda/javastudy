package com.cinapse.javastudy;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class VlanModelAssembler implements RepresentationModelAssembler<Vlan, EntityModel<Vlan>> {

    @Override
    public EntityModel<Vlan> toModel(Vlan vlan) {

        return EntityModel.of(vlan, //
                linkTo(methodOn(VlanController.class).one(vlan.getId())).withSelfRel(),
                linkTo(methodOn(VlanController.class).all()).withRel("vlans"));
    }
}
