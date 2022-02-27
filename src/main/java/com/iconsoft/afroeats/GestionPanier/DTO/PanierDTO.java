package com.iconsoft.afroeats.GestionPanier.DTO;

import java.util.List;

public class PanierDTO {
    private String reference;
    private String emailclient;
    private double montanttotal;
    private List<PanieritemDTO> panieritems;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getEmailclient() {
        return emailclient;
    }

    public void setEmailclient(String emailclient) {
        this.emailclient = emailclient;
    }

    public double getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(double montanttotal) {
        this.montanttotal = montanttotal;
    }

    public List<PanieritemDTO> getPanieritems() {
        return panieritems;
    }

    public void setPanieritems(List<PanieritemDTO> panieritems) {
        this.panieritems = panieritems;
    }
}
