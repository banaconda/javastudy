package com.cinapse.javastudy;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class VlanServiceImpl implements VlanService {
    private final VlanRepository repository;
    private final VlanModelAssembler assembler;

    VlanServiceImpl(VlanRepository repository, VlanModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @Override
    public EntityModel<Vlan> createVlan(Vlan vlan) {
        EntityModel<Vlan> entityModel = assembler.toModel(repository.save(vlan));
        return entityModel;
    }

    @Override
    public EntityModel<Vlan> updateVlan(Long id, Vlan newVlan) {
        Vlan updatedVlan = repository.findById(id) //
                .map(vlan -> {
                    vlan.setMgmtIp(newVlan.getMgmtIp());
                    vlan.setVlans(newVlan.getVlans());
                    return repository.save(vlan);
                })
                .orElseGet(() -> {
                    newVlan.setId(id);
                    return repository.save(newVlan);
                });

        EntityModel<Vlan> entityModel = assembler.toModel(updatedVlan);
        return entityModel;
    }

    @Override
    public void deleteVlan(Long id) {
        Vlan vlan = repository.findById(id) //
                .orElseThrow(() -> new VlanNotFoundException(id));

        repository.deleteById(id);
    }

    @Override
    public List<EntityModel<Vlan>> getVlan() {
        List<EntityModel<Vlan>> vlans = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return vlans;
    }

    @Override
    public EntityModel<Vlan> getVlan(Long id) {
        Vlan vlan = repository.findById(id) //
                .orElseThrow(() -> new VlanNotFoundException(id));

        return assembler.toModel(vlan);
    }

}
