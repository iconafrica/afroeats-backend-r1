package com.iconsoft.afroeats.GestionCommande.Dao;

import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findAllByNomclient(String name);
    @Query("select c from Commande as c where c.nomclient = ?1 and (c.statut='INITIEE' or c.statut='ACCEPTEE' or c.statut='PRET POUR LIVRAISON')")
    List<Commande> findAllByNomclientEncours(String name);
    @Query("select c from Commande as c where c.statut='INITIEE' or c.statut='ACCEPTEE' or c.statut='PRET POUR LIVRAISON'")
    List<Commande> findAllByEncours();
    Commande findByReferencecommande(String reference);
    List<Commande> findAllByReferenceclientOrderByDatecommandeDesc(String referenceClient);
}
