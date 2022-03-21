package com.cinapse.javastudy;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class SwitchServiceImpl implements SwitchService {
    private final SwitchRepository repository;
    private final SwitchModelAssembler assembler;

    SwitchServiceImpl(SwitchRepository repository, SwitchModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @Override
    public EntityModel<Switch> createSwitch(Switch sw) {
        EntityModel<Switch> entityModel = assembler.toModel(repository.save(sw));
        return entityModel;
    }

    @Override
    public EntityModel<Switch> updateSwitch(Long id, Switch newSwitch) {
        Switch updatedSwitch = repository.findById(id) //
                .map(sw -> {
                    sw.setIp(newSwitch.getIp());
                    sw.setUsername(newSwitch.getUsername());
                    sw.setPassword(newSwitch.getPassword());
                    return repository.save(sw);
                }) //
                .orElseGet(() -> {
                    newSwitch.setId(id);
                    return repository.save(newSwitch);
                });

        EntityModel<Switch> entityModel = assembler.toModel(updatedSwitch);
        return entityModel;
    }

    @Override
    public void deleteSwitch(Long id) {
        Switch sw = repository.findById(id) //
                .orElseThrow(() -> new SwitchNotFoundException(id));

        repository.deleteById(id);
    }

    @Override
    public List<EntityModel<Switch>> getSwitches() {
        List<EntityModel<Switch>> switches = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return switches;
    }

    @Override
    public EntityModel<Switch> getSwitches(Long id) {
        Switch sw = repository.findById(id) //
                .orElseThrow(() -> new SwitchNotFoundException(id));

        return assembler.toModel(sw);
    }
}
