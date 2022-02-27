package com.iconsoft.afroeats.GestionRestaurant.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionProduits.Models.Menus;
import com.iconsoft.afroeats.GestionProduits.Models.Plat;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "restaurant")
@PrimaryKeyJoinColumn(name = "idpointdeente")
public class Restaurant extends PointDeVente{
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    List<Plat> plats = new ArrayList<>();
    @JsonIgnore
    @OneToOne(mappedBy = "restaurant")
    private Afromama afromama;
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Menus> menus;

    public List<Plat> getPlats() {
        return plats;
    }

    public void setPlats(List<Plat> plats) {
        this.plats = plats;
    }

    public Afromama getAfromama() {
        return afromama;
    }

    public void setAfromama(Afromama afromama) {
        this.afromama = afromama;
    }

    public List<Menus> getMenus() {
        return menus;
    }

    public void setMenus(List<Menus> menus) {
        this.menus = menus;
    }
}
