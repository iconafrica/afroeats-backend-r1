package com.iconsoft.afroeats.GestionCommande.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionProduits.Models.Plat;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.DriverAfro;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "commandepartenaire")
public class Commandepartenaire {
    @Id @GeneratedValue
    private Long idcommandepartenaire;
    private Date daterecuperation;
    private boolean etatpreparation;
    @Column(unique = true)
    private String reference;
    private String referencecommande;
    private String referenceafromama;
    private String referencelivreur;
    private String nomclient;
    private String streetaddress;
    private String phoneaddress;
    private String streetnumber;
    private String cityaddress;
    private String lattitude;
    private String longiture;


    @Column(columnDefinition = "varchar(255) default 'NON_LIVREE'")
    private String etatlivraison;//livrer   non livrer    REJETEE
    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Plat plat;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idafromama")
    private Afromama afromama;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "iddriver")
    private DriverAfro driverAfro;
    private int nombredeplat;
    private double montant;

    public Long getIdcommandepartenaire() {
        return idcommandepartenaire;
    }

    public void setIdcommandepartenaire(Long idcommandeafromama) {
        this.idcommandepartenaire = idcommandeafromama;
    }

    public Date getDaterecuperation() {
        return daterecuperation;
    }

    public void setDaterecuperation(Date daterecuperation) {
        this.daterecuperation = daterecuperation;
    }

    public boolean isEtatpreparation() {
        return etatpreparation;
    }

    public void setEtatpreparation(boolean etatpreparation) {
        this.etatpreparation = etatpreparation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReferencecommande() {
        return referencecommande;
    }

    public void setReferencecommande(String referencecommande) {
        this.referencecommande = referencecommande;
    }

    public String getReferenceafromama() {
        return referenceafromama;
    }

    public void setReferenceafromama(String referenceafromama) {
        this.referenceafromama = referenceafromama;
    }

    public String getReferencelivreur() {
        return referencelivreur;
    }

    public void setReferencelivreur(String referencelivreur) {
        this.referencelivreur = referencelivreur;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public Afromama getAfromama() {
        return afromama;
    }

    public void setAfromama(Afromama afromama) {
        this.afromama = afromama;
    }

    public int getNombredeplat() {
        return nombredeplat;
    }

    public void setNombredeplat(int nombredeplat) {
        this.nombredeplat = nombredeplat;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public DriverAfro getDriverAfro() {
        return driverAfro;
    }

    public void setDriverAfro(DriverAfro driverAfro) {
        this.driverAfro = driverAfro;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getEtatlivraison() {
        return etatlivraison;
    }

    public void setEtatlivraison(String etatlivraison) {
        this.etatlivraison = etatlivraison;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    public String getPhoneaddress() {
        return phoneaddress;
    }

    public void setPhoneaddress(String phoneaddress) {
        this.phoneaddress = phoneaddress;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getCityaddress() {
        return cityaddress;
    }

    public void setCityaddress(String cityaddress) {
        this.cityaddress = cityaddress;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongiture() {
        return longiture;
    }

    public void setLongiture(String longiture) {
        this.longiture = longiture;
    }
}
