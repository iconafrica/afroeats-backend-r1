package com.iconsoft.afroeats.GestionCommande.Metier;

import com.iconsoft.afroeats.GestionCommande.DTO.CloturePlatDto;
import com.iconsoft.afroeats.GestionCommande.DTO.CmdLivreurDTO;
import com.iconsoft.afroeats.GestionCommande.DTO.CommandeDTO;
import com.iconsoft.afroeats.GestionCommande.DTO.DashboardCommande;
import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import com.iconsoft.afroeats.GestionCommande.Models.Commandepartenaire;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CommandeMetier {

    Commande ProceedCommand(CommandeDTO commandeDTO);
    Commande getCommandesByreference(String referencecommande);
    List<Commande> getCommandesByClient(String fullname);
    List<Commande> getCommandesByReferenceClient(String referenceclient);
    List<Commande> getCommandesEncoursByClient(String fullname);
    List<Commande> getCommandesByAfromama(String name);
    List<Commande> getAllCommandes();
    List<Commande> getAllCommandesEncours();
    Commande AcceptCommand(CommandeDTO commandeDTO);
    Commande RejectCommand(CommandeDTO commandeDTO);
    Commande LivreCommand(CommandeDTO commandeDTO);

    List<Commandepartenaire> findByReferenceafromama(String referenceAfromama);
    List<Commandepartenaire> findByReferencecommande(String referenceCommande);
    Commandepartenaire findByReference(String reference);
    Commandepartenaire terminerCommandeAfromama(String referencecommandepartenaire);
    List<Commandepartenaire> findByReferencelivreur(String referenceLivreur);
    List<Commandepartenaire> findAllcommandeTerminer();//cette methode recuper toutes les commandes que l'afromama a terminer
    Commandepartenaire associete_livreurTo_commande(String referencecommandeAfromama, String reference_livreur);
    boolean getEtatPayement(String referencecommande);
    Commandepartenaire changeEtalivraison(CloturePlatDto cloturePlatDto);
    DashboardCommande getdashboard();
    List<Commandepartenaire> findByReferencelivreurAndEtatlivraison(String referenceLivreur,String etat_livraison);

    List<CmdLivreurDTO> findCmdByReferencelivreur(String referenceliveur,String etatlivraison);
}
