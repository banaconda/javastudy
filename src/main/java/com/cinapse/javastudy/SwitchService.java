package com.cinapse.javastudy;

import java.util.List;

import org.springframework.hateoas.EntityModel;

public interface SwitchService {
    public abstract EntityModel<Switch> createSwitch(Switch sw);

    public abstract EntityModel<Switch> updateSwitch(Long id, Switch sw);

    public abstract void deleteSwitch(Long id);

    public abstract List<EntityModel<Switch>> getSwitches();

    public abstract EntityModel<Switch> getSwitches(Long id);
}
