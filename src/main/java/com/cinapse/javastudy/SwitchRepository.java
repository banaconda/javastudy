package com.cinapse.javastudy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SwitchRepository extends JpaRepository<Switch, Long> {

}
