package com.iconsoft.afroeats.GestionCommande.WebRestController;

import com.iconsoft.afroeats.GestionCommande.DTO.CloturePlatDto;
import com.iconsoft.afroeats.GestionCommande.DTO.CmdLivreurDTO;
import com.iconsoft.afroeats.GestionCommande.DTO.CommandeDTO;
import com.iconsoft.afroeats.GestionCommande.DTO.DashboardCommande;
import com.iconsoft.afroeats.GestionCommande.Metier.CommandeMetier;
import com.iconsoft.afroeats.GestionCommande.Models.Commande;
import com.iconsoft.afroeats.GestionCommande.Models.Commandepartenaire;
import com.iconsoft.afroeats.GestionCommande.Models.LivraisonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("commande")
@RestController
public class CommandeRestController {
    @Autowired
    CommandeMetier commandeMetier;

    @PostMapping("manage")
    public Commande ManageCommande(@RequestBody CommandeDTO commandeDTO) {
        if (commandeDTO.getTypeaction().equalsIgnoreCase("initie")) {
            return commandeMetier.ProceedCommand(commandeDTO);
        } else if (commandeDTO.getTypeaction().equalsIgnoreCase("accept")) {
            return commandeMetier.AcceptCommand(commandeDTO);
        } else if (commandeDTO.getTypeaction().equalsIgnoreCase("reject")) {
            return commandeMetier.RejectCommand(commandeDTO);
        } else if (commandeDTO.getTypeaction().equalsIgnoreCase("livre")) {
            return commandeMetier.LivreCommand(commandeDTO);
        } else return null;
    }

    @GetMapping("getcommandesbyclient/{fullname}")
    public List<Commande> getCommandesByClient(@PathVariable String fullname) {
        return commandeMetier.getCommandesByClient(fullname);
    }

    @GetMapping("getcommandesencoursbyclient/{fullname}")
    public List<Commande> getCommandesEncoursByClient(@PathVariable String fullname) {
        return commandeMetier.getCommandesEncoursByClient(fullname);
    }

    @GetMapping("getcommandesencoursbyreferenceclient/{referenceclient}")
    public List<Commande> getCommandesEncoursByReferenceClient(@PathVariable String referenceclient) {
        return commandeMetier.getCommandesByReferenceClient(referenceclient);
    }

    @GetMapping("getcommandebyreference/{referencecommande}")
    public Commande getCommandesByreference(@PathVariable String referencecommande) {
        return commandeMetier.getCommandesByreference(referencecommande);
    }

    @GetMapping("getallcommandes")
    public List<Commande> getAllCommandes() {
        return commandeMetier.getAllCommandes();
    }

    @GetMapping("getallcommandesencours")
    public List<Commande> getAllCommandesEncours() {
        return commandeMetier.getAllCommandesEncours();
    }


//ici ce sont les commandes du partenaire qui veut dire les commandes que le livreur voit si l'afromama a terminer de preparer
    @GetMapping("findallcommandeafromamaterminer/")
    public List<Commandepartenaire> findallcommandeafromamaterminer() {
        return commandeMetier.findAllcommandeTerminer();
    }
    @GetMapping("commandeafromama/{referencecommandepartenaire}")
    public List<Commandepartenaire> findByReferencecommande(@PathVariable String referencecommandepartenaire) {
        return commandeMetier.findByReferenceafromama(referencecommandepartenaire);
    }
    @GetMapping("terminercommandeafromama/{referencecommandepartenaire}")
    public Commandepartenaire terminerCommande(@PathVariable String referencecommandepartenaire) {
        return commandeMetier.terminerCommandeAfromama(referencecommandepartenaire);
    }
    @GetMapping("associete_livreurTo_commande/{referencecommandepartenaire}/{reference_livreur}")
    Commandepartenaire associete_livreurTo_commande(@PathVariable String referencecommandepartenaire, @PathVariable String reference_livreur){
        return commandeMetier.associete_livreurTo_commande(referencecommandepartenaire, reference_livreur);
    }

    //cette methode permet de dire si une commande a été payer ou pas

    @GetMapping("getetatpaiement/{referencecommande}")
    boolean getEtatPayement(@PathVariable String referencecommande){
        return commandeMetier.getEtatPayement(referencecommande);
    }



    /// les commande du livreur

    @GetMapping("commandeliveurs/{referenceliveur}")
    public List<Commandepartenaire> findByReferencelivreur(@PathVariable String referenceliveur) {
        return commandeMetier.findByReferencelivreur(referenceliveur);
    }
    @GetMapping("commandeliveurs/encours/{referenceliveur}")
    public List<CmdLivreurDTO> findByReferencelivreurNonTerminer(@PathVariable String referenceliveur) {
        return commandeMetier.findCmdByReferencelivreur(referenceliveur,LivraisonStatus.NON_LIVRER);
    }
    @GetMapping("commandeliveurs/terminer/{referenceliveur}")
    public List<CmdLivreurDTO> findByReferencelivreurTerminer(@PathVariable String referenceliveur) {
        return commandeMetier.findCmdByReferencelivreur(referenceliveur, LivraisonStatus.LIVRER);
    }

    @GetMapping("cmddliveurs/{referenceliveur}")
    public List<CmdLivreurDTO> findCmdByReferencelivreur(@PathVariable String referenceliveur) {
        return commandeMetier.findCmdByReferencelivreur(referenceliveur,null);
    }

    @PutMapping("commandeliveur/cloturer")
    public Commandepartenaire changeEtalivraison(@RequestBody CloturePlatDto cloturePlatDto) {
        return commandeMetier.changeEtalivraison(cloturePlatDto);
    }

    // Dashboard des commandes
    @GetMapping("dashoard")
    public DashboardCommande getdashboard() {
        return commandeMetier.getdashboard();
    }

    // Dashboard des commandes


}
