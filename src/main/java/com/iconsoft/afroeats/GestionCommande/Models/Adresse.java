package com.iconsoft.afroeats.GestionCommande.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Adresse implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idadresse;
    private String typeaddress;
    private String streetaddress;
    private String countrycode;
    private String streetnumber;
    private String libaddress;
    private String cityaddress;
    private String stateaddress;
    private String countryaddress;
    @Column(columnDefinition = "int default '0'")
    private int codepostal;
    private String quartieraddress;
    private String repereaddress;
    private String phoneaddress;
    private String lattitude;
    private String longiture;
    private Date  datedelivraison;
    @JsonIgnore
    @OneToOne(mappedBy = "adresse")
    private Commande commande;

    @OneToOne()
    @JoinColumn(name = "iduser")
    @JsonIgnore
    private AppUser appuser;

    public Long getIdadresse() {
        return idadresse;
    }

    public void setIdadresse(Long idadresse) {
        this.idadresse = idadresse;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getLibaddress() {
        return libaddress;
    }

    public void setLibaddress(String libaddress) {
        this.libaddress = libaddress;
    }

    public String getCityaddress() {
        return cityaddress;
    }

    public void setCityaddress(String cityaddress) {
        this.cityaddress = cityaddress;
    }

    public String getStateaddress() {
        return stateaddress;
    }

    public void setStateaddress(String stateaddress) {
        this.stateaddress = stateaddress;
    }

    public String getCountryaddress() {
        return countryaddress;
    }

    public void setCountryaddress(String countryaddress) {
        this.countryaddress = countryaddress;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public String getQuartieraddress() {
        return quartieraddress;
    }

    public void setQuartieraddress(String quartieraddress) {
        this.quartieraddress = quartieraddress;
    }

    public String getRepereaddress() {
        return repereaddress;
    }

    public void setRepereaddress(String repereaddress) {
        this.repereaddress = repereaddress;
    }

    public String getPhoneaddress() {
        return phoneaddress;
    }

    public void setPhoneaddress(String phoneaddress) {
        this.phoneaddress = phoneaddress;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commandes) {
        this.commande = commandes;
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

    public String getTypeaddress() {
        return typeaddress;
    }

    public void setTypeaddress(String typeaddress) {
        this.typeaddress = typeaddress;
    }

    public Date getDatedelivraison() {
        return datedelivraison;
    }

    public void setDatedelivraison(Date datedelivraison) {
        this.datedelivraison = datedelivraison;
    }

    public AppUser getAppuser() {
        return appuser;
    }

    public void setAppuser(AppUser appuser) {
        this.appuser = appuser;
    }
}
