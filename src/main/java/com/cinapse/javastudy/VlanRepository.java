package com.cinapse.javastudy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VlanRepository extends JpaRepository<Vlan, Long> {
    
}
