package com.iconsoft.afroeats.GestionCommande.DTO;

public class CloturePlatDto {
    private String referencecommandpartenaire;
    private String referencelivreur;
    private String referencecommande;
    private String codecloture;
    private String etat_livraison;

    public String getReferencecommandpartenaire() {
        return referencecommandpartenaire;
    }

    public void setReferencecommandpartenaire(String referencecommandpartenaire) {
        this.referencecommandpartenaire = referencecommandpartenaire;
    }

    public String getReferencelivreur() {
        return referencelivreur;
    }

    public void setReferencelivreur(String referencelivreur) {
        this.referencelivreur = referencelivreur;
    }

    public String getReferencecommande() {
        return referencecommande;
    }

    public void setReferencecommande(String referencecommande) {
        this.referencecommande = referencecommande;
    }

    public String getCodecloture() {
        return codecloture;
    }

    public void setCodecloture(String codecloture) {
        this.codecloture = codecloture;
    }

    public String getEtat_livraison() {
        return etat_livraison;
    }

    public void setEtat_livraison(String etat_livraison) {
        this.etat_livraison = etat_livraison;
    }
}
