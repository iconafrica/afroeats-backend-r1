package com.iconsoft.afroeats.GestionLivraison.Model;

public class ResponseFraisLivraison {
    private double fraislivraison=0;
    private String raison;

    public double getFraislivraison() {
        return fraislivraison;
    }

    public void setFraislivraison(double fraislivraison) {
        this.fraislivraison = fraislivraison;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }
}
