package com.iconsoft.afroeats.GestionCommande.DTO;

import com.iconsoft.afroeats.GestionCommande.Models.Commandepartenaire;

import java.util.ArrayList;
import java.util.List;

public class CmdLivreurDTO {
    private String referencecommande;
    private String photo_client;
    private List<Commandepartenaire> commandepartenaires = new ArrayList<>();

    public String getReferencecommande() {
        return referencecommande;
    }

    public void setReferencecommande(String referencecommande) {
        this.referencecommande = referencecommande;
    }

    public List<Commandepartenaire> getCommandepartenaires() {
        return commandepartenaires;
    }

    public void setCommandepartenaires(List<Commandepartenaire> commandepartenaires) {
        this.commandepartenaires = commandepartenaires;
    }

    public String getPhoto_client() {
        return photo_client;
    }

    public void setPhoto_client(String photo_client) {
        this.photo_client = photo_client;
    }
}
