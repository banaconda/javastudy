package com.cinapse.javastudy;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class SwitchModelAssembler implements RepresentationModelAssembler<Switch, EntityModel<Switch>> {

    @Override
    public EntityModel<Switch> toModel(Switch sw) {

        return EntityModel.of(sw, //
                linkTo(methodOn(SwitchController.class).one(sw.getId())).withSelfRel(),
                linkTo(methodOn(SwitchController.class).all()).withRel("switches"));
    }
}
