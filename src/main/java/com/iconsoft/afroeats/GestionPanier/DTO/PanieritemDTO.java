package com.iconsoft.afroeats.GestionPanier.DTO;

public class PanieritemDTO {
    private Long idarticle;
    private int quantite;
    private double prixunitaire;
    private String description;
    private String typeaction; // valeur de test : add (Ajouter et augmenter) or remove (reduire) or delete
    private String reference;
    private String typeproduit; //plat      produit
    private String jour;
    public Long getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(Long idarticle) {
        this.idarticle = idarticle;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeaction() {
        return typeaction;
    }

    public void setTypeaction(String typeaction) {
        this.typeaction = typeaction;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }
}
