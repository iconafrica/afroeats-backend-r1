package com.iconsoft.afroeats.GestionProduits.DTO;

import com.iconsoft.afroeats.GestionProduits.Models.Plat;

public class PlatJourDto {
    private Plat plat;
    private String jours;

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public String getJours() {
        return jours;
    }

    public void setJours(String jours) {
        this.jours = jours;
    }
}
