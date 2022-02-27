package com.iconsoft.afroeats.GestionRestaurant.Dto;

import javax.persistence.Column;

public class PointVenteSaveDTO {
    private String libelle;
    private String image;
    private double longitude;
    private double latitude;
    private String description;
    private String referenceafro;
    private String typevente;//restaurant ou vivre frais

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceafro() {
        return referenceafro;
    }

    public void setReferenceafro(String referenceafro) {
        this.referenceafro = referenceafro;
    }

    public String getTypevente() {
        return typevente;
    }

    public void setTypevente(String typevente) {
        this.typevente = typevente;
    }
}
