package com.iconsoft.afroeats.GestionPanier.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.ClientAfro;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Panier implements Serializable {
    @Id
    @GeneratedValue
    private Long idpanier;
    private String reference;
    private double montanttotal;
    @JsonFormat(timezone = "GMT+01:00")
    private Date datecreation;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "iduser")
    private ClientAfro client;
    private String nomclient;
    @OneToMany(mappedBy = "panier")
    private List<Panieritem> panieritems;

    public void setIdpanier(Long idpanier) {
        this.idpanier = idpanier;
    }

    public Long getIdpanier() {
        return idpanier;
    }

    public double getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(double montanttotal) {
        this.montanttotal = montanttotal;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public ClientAfro getClient() {
        return client;
    }

    public void setClient(ClientAfro client) {
        this.client = client;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Panieritem> getPanieritems() {
        return panieritems;
    }

    public void setPanieritems(List<Panieritem> panieritems) {
        this.panieritems = panieritems;
    }
}
