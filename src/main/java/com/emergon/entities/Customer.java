package com.emergon.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "customer")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ccode;
    @NotEmpty
    @Column(name = "cname")
    private String name;

    public Customer() {
    }

    public Customer(Integer ccode, String name) {
        this.ccode = ccode;
        this.name = name;
    }

    public Customer(String name) {
        this.name = name;
    }

    public Integer getCcode() {
        return ccode;
    }

    public void setCcode(Integer ccode) {
        this.ccode = ccode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.ccode);
        hash = 41 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.ccode, other.ccode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "ccode=" + ccode + ", name=" + name + '}';
    }
    
    
}
