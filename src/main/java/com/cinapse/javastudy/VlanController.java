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
class VlanController {
    @Autowired
    VlanService service;

    @GetMapping("/vlans")
    CollectionModel<EntityModel<Vlan>> all() {

        List<EntityModel<Vlan>> vlans = service.getVlan();

        return CollectionModel.of(vlans, linkTo(methodOn(VlanController.class).all()).withSelfRel());
    }
    
    @PostMapping("/vlans")
    ResponseEntity<?> newVlan(@RequestBody Vlan newVlan) {
        EntityModel<Vlan> entityModel = service.createVlan(newVlan);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/vlans/{id}")
    EntityModel<Vlan> one(@PathVariable Long id) {
        return service.getVlan(id);
    }

    @PutMapping("/vlans/{id}")
    ResponseEntity<?> replaceVlan(@RequestBody Vlan newVlan, @PathVariable Long id) {

        EntityModel<Vlan> entityModel = service.updateVlan(id, newVlan);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/vlans/{id}")
    ResponseEntity<?> deleteVlan(@PathVariable Long id) {
        service.deleteVlan(id);
        return ResponseEntity.noContent().build();
    }
}
