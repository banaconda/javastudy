package com.cinapse.javastudy;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
class Vlan {

    private @Id @GeneratedValue Long id;
    String mgmtIp;
    String vlans;


    Vlan() {
    }

    Vlan(String mgmtIp, String vlans) {
        this.mgmtIp = mgmtIp;
        this.vlans = vlans;

    }
    
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Vlan))
            return false;
        Vlan vlan = (Vlan) o;
        return Objects.equals(this.mgmtIp, vlan.mgmtIp) && Objects.equals(this.vlans, vlan.vlans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mgmtIp, this.vlans);
    }

    @Override
    public String toString() {
        return "Vlan{" + "mgmtIp=" + this.mgmtIp + ", " + "vlans=" + this.vlans + "}";
    }
}