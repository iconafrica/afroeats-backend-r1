package com.iconsoft.afroeats.GestionProduits.DTO;

import com.iconsoft.afroeats.GestionProduits.Models.Categorie;
import com.iconsoft.afroeats.GestionProduits.Models.Plattiming;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;

import java.util.Date;

public class ArticleDTO {
   private Long idarticle;
    private String reference;
    private Boolean status;
    private  String nomarticle;
    private  String descriptionarticle;
    private   Double prixarticle;
    private   Boolean vegetarien;
    private  Date createdat;
    private  Date updateat;
    private   String imagearticle;
    private  Categorie categorie;
    private   String nomcategorie;
    private    String typearticle; //plat; produit ...
    Restaurant restaurant;
    String reference_restaurant;
    Plattiming plattiming;
    String action; //create; update; delete;
    Boolean disponibilite;

    public Long getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(Long idarticle) {
        this.idarticle = idarticle;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNomarticle() {
        return nomarticle;
    }

    public void setNomarticle(String nomarticle) {
        this.nomarticle = nomarticle;
    }

    public String getDescriptionarticle() {
        return descriptionarticle;
    }

    public void setDescriptionarticle(String descriptionarticle) {
        this.descriptionarticle = descriptionarticle;
    }

    public Double getPrixarticle() {
        return prixarticle;
    }

    public void setPrixarticle(Double prixarticle) {
        this.prixarticle = prixarticle;
    }

    public Boolean getVegetarien() {
        return vegetarien;
    }

    public void setVegetarien(Boolean vegetarien) {
        this.vegetarien = vegetarien;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    public String getImagearticle() {
        return imagearticle;
    }

    public void setImagearticle(String imagearticle) {
        this.imagearticle = imagearticle;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public String getTypearticle() {
        return typearticle;
    }

    public void setTypearticle(String typearticle) {
        this.typearticle = typearticle;
    }

    public Plattiming getPlattiming() {
        return plattiming;
    }

    public void setPlattiming(Plattiming plattiming) {
        this.plattiming = plattiming;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getReference_restaurant() {
        return reference_restaurant;
    }

    public void setReference_restaurant(String reference_restaurant) {
        this.reference_restaurant = reference_restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
}
