package com.cinapse.javastudy;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
class Switch {

    private @Id @GeneratedValue Long id;
    private String ip;
    private String username;
    private String password;

    Switch() {
    }

    Switch(String ip, String username, String password) {
        this.ip = ip;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Switch))
            return false;
        Switch sw = (Switch) o;
        return Objects.equals(this.id, sw.id) && Objects.equals(this.ip, sw.ip)
                && Objects.equals(this.username, sw.username) && Objects.equals(this.password, sw.password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.ip, this.username, this.password);
    }

    @Override
    public String toString() {
        return "Switch{" + "id=" + this.id + ", ip='" + this.ip + '\'' + ", username='" + this.username
                + '\'' + ", password='" + this.password + '\'' + '}';
    }
}