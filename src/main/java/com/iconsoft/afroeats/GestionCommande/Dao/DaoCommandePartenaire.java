package com.iconsoft.afroeats.GestionCommande.Dao;

import com.iconsoft.afroeats.GestionCommande.Models.Commandepartenaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoCommandePartenaire extends JpaRepository<Commandepartenaire,Long> {
    List<Commandepartenaire> findByReferenceafromama(String referenceAfromama);
    List<Commandepartenaire> findByReferencecommande(String referenceCommande);
    Commandepartenaire findByReference(String reference);
    List<Commandepartenaire> findByReferencelivreur(String referenceLivreur);
    List<Commandepartenaire> findByEtatpreparationIsTrue();
    int countAllByReferenceafromamaIsNotNull();
    int countAllByReferencelivreurIsNotNull();
    int countAllByReferenceIsNotNull();
    List<Commandepartenaire> findByReferencelivreurAndEtatlivraison(String referenceLivreur,String etat_livraison);
}
