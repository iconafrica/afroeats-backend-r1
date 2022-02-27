package com.iconsoft.afroeats.GestionProduits.DTO;

import javax.persistence.Column;

public class CategorieDTO {
    Long idcategorie;
    String nomcategorie;
    String imagecategorie;

    public Long getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Long idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public String getImagecategorie() {
        return imagecategorie;
    }

    public void setImagecategorie(String imagecategorie) {
        this.imagecategorie = imagecategorie;
    }
}
