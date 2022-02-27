package com.iconsoft.afroeats.GestionUtilisateurs.Models;


import com.iconsoft.afroeats.GestionCommande.Models.Commandepartenaire;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.EtatEvolutionDoccument;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "iduser")
@Table(name = "driverafro")
public class DriverAfro extends AppUser{
    @Column(columnDefinition = "text")
    private String identitydriver1;//cni recto
    @Column(columnDefinition = "text")
    private String identitydriver2;//cni verso
    @Column(columnDefinition = "text")
    private String adresseimage;// justificatif de l adresse une facture
    @Column(columnDefinition = "text")
    private String licencedriver;//licence du conducteur
    @Column(columnDefinition = "text")
    private String assurancedriver;//assurance des vehecule
    @Column(columnDefinition = "text")
    private String cardriver;//enregistremnet des vehicule
    private String modelivraison;   //A PIED   EN MOTO   EN VOITURE
    private boolean confirmregister;
    private boolean disponible;
    @OneToMany(mappedBy = "driverAfro")
    private List<Commandepartenaire> commandepartenaires;
    private EtatEvolutionDoccument etatevolutiondoc;//etat d'evolution des doccuments fournis

    public boolean isConfirmregister() {
        return confirmregister;
    }

    public void setConfirmregister(boolean confirm) {
        this.confirmregister = confirm;
    }

    public String getIdentitydriver1() {
        return identitydriver1;
    }

    public void setIdentitydriver1(String identitydriver1) {
        this.identitydriver1 = identitydriver1;
    }

    public String getIdentitydriver2() {
        return identitydriver2;
    }

    public void setIdentitydriver2(String identitydriver2) {
        this.identitydriver2 = identitydriver2;
    }

    public String getAdresseimage() {
        return adresseimage;
    }

    public void setAdresseimage(String adresseimage) {
        this.adresseimage = adresseimage;
    }

    public String getLicencedriver() {
        return licencedriver;
    }

    public void setLicencedriver(String licencedriver) {
        this.licencedriver = licencedriver;
    }

    public String getAssurancedriver() {
        return assurancedriver;
    }

    public void setAssurancedriver(String assurancedriver) {
        this.assurancedriver = assurancedriver;
    }

    public String getCardriver() {
        return cardriver;
    }

    public void setCardriver(String cardriver) {
        this.cardriver = cardriver;
    }

    public String getModelivraison() {
        return modelivraison;
    }

    public void setModelivraison(String modelivraison) {
        this.modelivraison = modelivraison;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public EtatEvolutionDoccument getEtatevolutiondoc() {
        return etatevolutiondoc;
    }

    public void setEtatevolutiondoc(EtatEvolutionDoccument etatevolutiondoc) {
        this.etatevolutiondoc = etatevolutiondoc;
    }

    public List<Commandepartenaire> getCommandepartenaires() {
        return commandepartenaires;
    }

    public void setCommandepartenaires(List<Commandepartenaire> commandepartenaires) {
        this.commandepartenaires = commandepartenaires;
    }
}
