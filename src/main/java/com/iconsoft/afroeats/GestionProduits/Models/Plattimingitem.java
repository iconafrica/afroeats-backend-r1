package com.iconsoft.afroeats.GestionProduits.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity(name = "plattimingitem")
public class Plattimingitem implements Serializable {
    @Id
    @GeneratedValue
    Long idplattimingitem;
    String reference;
    String jour;
    @Column(columnDefinition = "TIME")
    LocalTime debutdisponiblite;
    @Column(columnDefinition = "TIME")
    LocalTime findisponiblite;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idplattiming")
    Plattiming plattiming;

    public Long getIdplattimingitem() {
        return idplattimingitem;
    }

    public void setIdplattimingitem(Long idplattimingitem) {
        this.idplattimingitem = idplattimingitem;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public LocalTime getDebutdisponiblite() {
        return debutdisponiblite;
    }

    public void setDebutdisponiblite(LocalTime debutdisponiblite) {
        this.debutdisponiblite = debutdisponiblite;
    }

    public LocalTime getFindisponiblite() {
        return findisponiblite;
    }

    public void setFindisponiblite(LocalTime findisponiblite) {
        this.findisponiblite = findisponiblite;
    }

    public Plattiming getPlattiming() {
        return plattiming;
    }

    public void setPlattiming(Plattiming plattiming) {
        this.plattiming = plattiming;
    }
}
