package com.iconsoft.afroeats.GestionPaiements.Payment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionCommande.Models.Commande;

import javax.persistence.*;

@Entity
public class PaymentAfroeats {
    @Id @GeneratedValue
    private Long idpayment;
    @Column(columnDefinition = "text")
    private String url_redict_topayment;
    private double amount;
    private String referencecommande;
    private String paymentreference;
    private String type_payment;//paypal ou stripe ou visa
    private String currency;//USD
    private String state;//Pending
    @OneToOne(mappedBy = "paymentAfroeats")
    @JsonIgnore
    private Commande commande;
    public Long getIdpayment() {
        return idpayment;
    }

    public void setIdpayment(Long idpayment) {
        this.idpayment = idpayment;
    }

    public String getUrl_redict_topayment() {
        return url_redict_topayment;
    }

    public void setUrl_redict_topayment(String urlexecuitepayment) {
        this.url_redict_topayment = urlexecuitepayment;
    }


    public String getType_payment() {
        return type_payment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType_payment(String methode) {
        this.type_payment = methode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPaymentreference() {
        return paymentreference;
    }

    public void setPaymentreference(String paymentreference) {
        this.paymentreference = paymentreference;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public String getReferencecommande() {
        return referencecommande;
    }

    public void setReferencecommande(String referencecommande) {
        this.referencecommande = referencecommande;
    }
}
