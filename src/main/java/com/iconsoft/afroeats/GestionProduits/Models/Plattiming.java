package com.iconsoft.afroeats.GestionProduits.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity(name = "plattiming")
public class Plattiming implements Serializable {
    @Id
    @GeneratedValue
    Long idplattiming;
    String reference;
    Date createdat;
    @OneToMany(mappedBy = "plattiming")
    Collection<Plattimingitem> plattimingitems = new ArrayList<>();

    public Plattiming() {
    }

    public Plattiming(String reference, Date createdat) {
        this.reference = reference;
        this.createdat = createdat;
        this.plattimingitems = plattimingitems;
    }

    public Long getIdplattiming() {
        return idplattiming;
    }

    public void setIdplattiming(Long idplattiming) {
        this.idplattiming = idplattiming;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Collection<Plattimingitem> getPlattimingitems() {
        return plattimingitems;
    }

    public void setPlattimingitems(Collection<Plattimingitem> plattimingitems) {
        this.plattimingitems = plattimingitems;
    }
}
