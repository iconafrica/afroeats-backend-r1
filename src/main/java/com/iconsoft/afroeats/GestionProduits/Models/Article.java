package com.iconsoft.afroeats.GestionProduits.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionPanier.Models.Panieritem;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "article")
@Inheritance(strategy = InheritanceType.JOINED)
public class Article implements Serializable {
    @Id
    @GeneratedValue
    Long idarticle;
    String reference;
    Boolean status;
    String nomarticle;
    @Column(columnDefinition = "text")
    String descriptionarticle;
    Double prixarticle;
    Boolean vegetarien;
    Date createdat;
    Date updateat;
    @Transient
    Restaurant restaurant;
    @Column(columnDefinition = "text")
    String imagearticle;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idcategorie")
    @JsonIgnore
    Categorie categorie;
    String nomcategorie;
    String typearticle; //plat; produit ...
    Boolean disponibilite;
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private Set<Panieritem> panieritems;

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

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Set<Panieritem> getPanieritems() {
        return panieritems;
    }

    public void setPanieritems(Set<Panieritem> panieritems) {
        this.panieritems = panieritems;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
