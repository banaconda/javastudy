package com.cinapse.javastudy;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.print.attribute.standard.Severity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SwitchController {
    @Autowired
    SwitchService service;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/switches")
    CollectionModel<EntityModel<Switch>> all() {

        List<EntityModel<Switch>> switches = service.getSwitches();

        return CollectionModel.of(switches, linkTo(methodOn(SwitchController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/switches")
    ResponseEntity<?> newSwitch(@RequestBody Switch newSwitch) {
        EntityModel<Switch> entityModel = service.createSwitch(newSwitch);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // Single item

    @GetMapping("/switches/{id}")
    EntityModel<Switch> one(@PathVariable Long id) {
        return service.getSwitches(id);
    }

    @PutMapping("/switches/{id}")
    ResponseEntity<?> replaceSwitch(@RequestBody Switch newSwitch, @PathVariable Long id) {

        EntityModel<Switch> entityModel = service.updateSwitch(id, newSwitch);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/switches/{id}")
    ResponseEntity<?> deleteSwitch(@PathVariable Long id) {
        service.deleteSwitch(id);
        return ResponseEntity.noContent().build();
    }
}
