package com.iconsoft.afroeats.GestionUtilisateurs.Models;


import com.iconsoft.afroeats.GestionCommande.Models.Commandepartenaire;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "iduser")
public class Afromama extends AppUser{
    @Column(columnDefinition = "text")
    private String cni1;
    @Column(columnDefinition = "text")
    private String cn2;
    private boolean confirmregister;
    private String typeafromama; //Les valeurs sont présentes dans la classe TypeAfromama
    @OneToOne
    @JoinColumn(name = "idpointdeente")
    Restaurant restaurant;
    @OneToMany(mappedBy = "afromama")
    private List<Commandepartenaire> commandepartenaires;
    public String getCni1() {
        return cni1;
    }

    public void setCni1(String cni1) {
        this.cni1 = cni1;
    }

    public String getCn2() {
        return cn2;
    }

    public void setCn2(String cn2) {
        this.cn2 = cn2;
    }

    public boolean isConfirmregister() {
        return confirmregister;
    }

    public void setConfirmregister(boolean confirm) {
        this.confirmregister = confirm;
    }

    public String getTypeafromama() {
        return typeafromama;
    }

    public void setTypeafromama(String typeafromama) {
        this.typeafromama = typeafromama;
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
