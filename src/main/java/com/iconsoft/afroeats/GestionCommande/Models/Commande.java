package com.iconsoft.afroeats.GestionCommande.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionPaiements.Payment.Model.PaymentAfroeats;
import com.iconsoft.afroeats.GestionPanier.Models.Panieritem;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.ClientAfro;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue
    private Long idcommande;
    private String referencecommande;
    private double montantcommande;
    private String statut;
    private boolean accepted;
    @JsonFormat(timezone = "GMT+01:00")
    private Date datecommande;
    private String modecommande;
    private double fraislivraison;
  //  @Column(columnDefinition = "float default '0'")
    private double montantttc;
  //  @Column(columnDefinition = "float default '0'")
    private double montantht;
 //   @Column(columnDefinition = "float default '0'")
    private double montanttva;
    private String codecloture;
    private double netapayer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "iduser")//ici c'est l'id de la personne qui passe la commande
    private ClientAfro client;
    private String nomclient;
    @OneToMany(mappedBy = "commandeitem",cascade = CascadeType.REMOVE)
    private List<Panieritem> panieritems=new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "idadresse")
    private Adresse adresse;
    private String referenceclient;
    private Date updateat;
    @OneToOne
    @JoinColumn(name = "idpayment")
    private PaymentAfroeats paymentAfroeats;

    public void setIdcommande(Long idcommande) {
        this.idcommande = idcommande;
    }

    public Long getIdcommande() {
        return idcommande;
    }

    public String getReferencecommande() {
        return referencecommande;
    }

    public void setReferencecommande(String referencecommande) {
        this.referencecommande = referencecommande;
    }

    public double getMontantcommande() {
        return montantcommande;
    }

    public void setMontantcommande(double montantcommande) {
        this.montantcommande = montantcommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }

    public String getModecommande() {
        return modecommande;
    }

    public void setModecommande(String modecommande) {
        this.modecommande = modecommande;
    }

    public double getFraislivraison() {
        return fraislivraison;
    }

    public void setFraislivraison(double fraislivraison) {
        this.fraislivraison = fraislivraison;
    }

    public double getMontantttc() {
        return montantttc;
    }

    public void setMontantttc(double montantttc) {
        this.montantttc = montantttc;
    }

    public double getMontantht() {
        return montantht;
    }

    public void setMontantht(double montantht) {
        this.montantht = montantht;
    }

    public double getMontanttva() {
        return montanttva;
    }

    public void setMontanttva(double montanttva) {
        this.montanttva = montanttva;
    }

    public double getNetapayer() {
        return netapayer;
    }

    public void setNetapayer(double netapayer) {
        this.netapayer = netapayer;
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

    public List<Panieritem> getPanieritems() {
        return panieritems;
    }

    public void setPanieritems(List<Panieritem> panieritems) {
        this.panieritems = panieritems;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getReferenceclient() {
        return referenceclient;
    }

    public void setReferenceclient(String referenceclient) {
        this.referenceclient = referenceclient;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    public String getCodecloture() {
        return codecloture;
    }

    public void setCodecloture(String codecloture) {
        this.codecloture = codecloture;
    }

    public PaymentAfroeats getPaymentAfroeats() {
        return paymentAfroeats;
    }

    public void setPaymentAfroeats(PaymentAfroeats paymentAfroeats) {
        this.paymentAfroeats = paymentAfroeats;
    }
}
