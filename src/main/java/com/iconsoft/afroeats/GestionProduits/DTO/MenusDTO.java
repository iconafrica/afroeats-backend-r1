package com.iconsoft.afroeats.GestionProduits.DTO;

import com.iconsoft.afroeats.GestionProduits.Models.Menus;
import com.iconsoft.afroeats.GestionProduits.Models.Plat;

import java.util.Date;
import java.util.List;

public class MenusDTO {
    private Long idmenus;
    private String reference;
    private String jour; //MONDAY   TUESDAY   WEDNESDAY   THURSDAY  FRIDAY  SATURDAY   SUNDAY
    private Date createdat;
    private List<Plat> plats;
    private String referencerestaurant;
    private String libellerestaurant;

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


    public String getReferencerestaurant() {
        return referencerestaurant;
    }

    public void setReferencerestaurant(String referencerestaurant) {
        this.referencerestaurant = referencerestaurant;
    }

    public String getLibellerestaurant() {
        return libellerestaurant;
    }

    public void setLibellerestaurant(String libellerestaurant) {
        this.libellerestaurant = libellerestaurant;
    }
}
