package com.iconsoft.afroeats.GestionProduits.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Menus implements Serializable {
    @Id
    @GeneratedValue
    private Long idmenus;
    private String reference;
    private String jour;
    private int numero;
    private Date createdat;
    private String referencerestaurant;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idrestaurant")
    private Restaurant restaurant;
    @Fetch(value = FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Plat> plats=new ArrayList<>();

    public Long getIdmenus() {
        return idmenus;
    }

    public void setIdmenus(Long idmenus) {
        this.idmenus = idmenus;
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

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public void setPlats(List<Plat> plats) {
        this.plats = plats;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getReferencerestaurant() {
        return referencerestaurant;
    }

    public void setReferencerestaurant(String referenceafromama) {
        this.referencerestaurant = referenceafromama;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
