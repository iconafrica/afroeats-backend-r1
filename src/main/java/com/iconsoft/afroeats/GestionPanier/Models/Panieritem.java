package com.iconsoft.afroeats.GestionPanier.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import com.iconsoft.afroeats.GestionProduits.Models.Article;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Panieritem implements Serializable {
    @Id
    @GeneratedValue
    private Long idpanieritem;
    private int quantite;
    private double prixunitaire;
    private double montant;
    private String description;
    @Column(columnDefinition = "varchar(255) default 'LUNDI'")
    private String jour;
    @Column(columnDefinition = "varchar(255) default 'NON_LIVREE'")
    private String statuslivraison;
    @JsonFormat(timezone = "GMT+01:00")
    private Date datecreation;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idpanier")
    private Panier panier;
    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;
    private String nomarticle;
    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idcommande")
    private Commande commandeitem;

    public void setIdpanieritem(Long idpanieritem) {
        this.idpanieritem = idpanieritem;
    }

    public Long getIdpanieritem() {
        return idpanieritem;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getNomarticle() {
        return nomarticle;
    }

    public void setNomarticle(String nomarticle) {
        this.nomarticle = nomarticle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(double prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Commande getCommandeitem() {
        return commandeitem;
    }

    public void setCommandeitem(Commande commandeitem) {
        this.commandeitem = commandeitem;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getStatuslivraison() {
        return statuslivraison;
    }

    public void setStatuslivraison(String statuslivraison) {
        this.statuslivraison = statuslivraison;
    }
}
