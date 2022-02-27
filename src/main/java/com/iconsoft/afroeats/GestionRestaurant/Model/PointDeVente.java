package com.iconsoft.afroeats.GestionRestaurant.Model;

import javax.persistence.*;

@Entity(name = "poindeente")
@Inheritance(strategy = InheritanceType.JOINED)
public class PointDeVente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpointdeente;
    private String libelle;
    @Column(columnDefinition = "text")
    private String image;
    private double longitude;
    private double latitude;
    @Column(columnDefinition = "text")
    private String description;
    @Column(unique = true,nullable = false)
    private String reference;
    private boolean status=true;

    public Long getIdpointdeente() {
        return idpointdeente;
    }

    public void setIdpointdeente(Long idpointdeente) {
        this.idpointdeente = idpointdeente;
    }

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
