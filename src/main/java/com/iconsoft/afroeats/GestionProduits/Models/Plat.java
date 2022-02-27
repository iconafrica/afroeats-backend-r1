package com.iconsoft.afroeats.GestionProduits.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionCommande.Models.Commandepartenaire;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;

import javax.persistence.*;
import java.util.List;

@Entity(name = "plat")
@PrimaryKeyJoinColumn(name = "idarticle")
public class Plat extends Article{

    int preparationtime; //Temps de préparation par heure. (Un entier)
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idpointdeente")
    private Restaurant restaurant;
    String nomafromama;
    @JsonIgnore
    @OneToMany(mappedBy = "plat")
    private List<Commandepartenaire> commandepartenaires;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idplattiming")
    Plattiming plattiming;



    public Plattiming getPlattiming() {
        return plattiming;
    }

    public void setPlattiming(Plattiming plattiming) {
        this.plattiming = plattiming;
    }

    public int getPreparationtime() {
        return preparationtime;
    }

    public void setPreparationtime(int preparationtime) {
        this.preparationtime = preparationtime;
    }

    public String getNomafromama() {
        return nomafromama;
    }

    public void setNomafromama(String nomafromama) {
        this.nomafromama = nomafromama;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Commandepartenaire> getCommandepartenaires() {
        return commandepartenaires;
    }

    public void setCommandepartenaires(List<Commandepartenaire> commandepartenaires) {
        this.commandepartenaires = commandepartenaires;
    }
}
