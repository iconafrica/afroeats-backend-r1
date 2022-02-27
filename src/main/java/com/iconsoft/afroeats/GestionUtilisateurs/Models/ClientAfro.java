package com.iconsoft.afroeats.GestionUtilisateurs.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import com.iconsoft.afroeats.GestionPanier.Models.Panier;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "iduser")
@Table(name = "clientafro")
public class ClientAfro extends AppUser{

    @OneToOne(mappedBy = "client",cascade = CascadeType.REMOVE)
    private Panier panier;
    @JsonIgnore
    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<Commande> commandes;

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }
}
