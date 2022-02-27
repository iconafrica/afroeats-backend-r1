package com.iconsoft.afroeats.GestionProduits.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity(name = "categorie")
public class Categorie implements Serializable {
    @Id
    @GeneratedValue
    Long idcategorie;
    @Column(unique = true)
    String nomcategorie;
    @Column(columnDefinition = "text")
    String imagecategorie;
    Date createdat;
    Date updateat;
    private String reference;
    @OneToMany(mappedBy = "categorie")
    Collection<Article> articles = new ArrayList<>();

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

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
