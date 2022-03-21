package com.cinapse.javastudy;

import java.util.List;

import org.springframework.hateoas.EntityModel;

public interface VlanService {
    public abstract EntityModel<Vlan> createVlan(Vlan vlan);

    public abstract EntityModel<Vlan> updateVlan(Long id, Vlan vlan);

    public abstract void deleteVlan(Long id);

    public abstract List<EntityModel<Vlan>> getVlan();

    public abstract EntityModel<Vlan> getVlan(Long id);
}
