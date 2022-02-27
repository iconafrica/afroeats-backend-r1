package com.iconsoft.afroeats.GestionCommande.Services;

import com.iconsoft.afroeats.Configuration.GenerateRandom;
import com.iconsoft.afroeats.GestionCommande.DTO.CloturePlatDto;
import com.iconsoft.afroeats.GestionCommande.DTO.CmdLivreurDTO;
import com.iconsoft.afroeats.GestionCommande.DTO.CommandeDTO;
import com.iconsoft.afroeats.GestionCommande.DTO.DashboardCommande;
import com.iconsoft.afroeats.GestionCommande.Dao.DaoCommandePartenaire;
import com.iconsoft.afroeats.GestionCommande.Metier.CommandeMetier;
import com.iconsoft.afroeats.GestionCommande.Models.*;
import com.iconsoft.afroeats.GestionCommande.Dao.AdresseRepository;
import com.iconsoft.afroeats.GestionCommande.Dao.CommandeRepository;
import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionNotification.GestionEmail.Service.MyAuthentication;
import com.iconsoft.afroeats.GestionPanier.Models.Panieritem;
import com.iconsoft.afroeats.GestionPanier.Repository.PanieritemRepository;
import com.iconsoft.afroeats.GestionProduits.Models.Plat;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppUserMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.ClientAfro;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.DriverAfro;
import com.iconsoft.afroeats.GestionUtilisateurs.Repository.ClientAfroRepository;
import com.iconsoft.afroeats.GestionUtilisateurs.Repository.DriverAfroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CommandeServices implements CommandeMetier {

    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    ClientAfroRepository clientAfroRepository;
    @Autowired
    PanieritemRepository panieritemRepository;
    @Autowired
    AdresseRepository adresseRepository;
    @Autowired
    DaoCommandePartenaire daoCommandePartenaire;
    @Autowired
    DriverAfroRepository driverAfroRepository;
    @Autowired
    AppUserMetier appUserMetier;
    @Autowired
    private MyAuthentication myAuthentication;

    @Override
    public Commande ProceedCommand(CommandeDTO commandeDTO) {
        Commande commande;

        if(commandeDTO.getIdcommande() == null && commandeDTO.getReferencecommande() == null){
            ClientAfro client = clientAfroRepository.findByReference(commandeDTO.getReferenceclient());
            if (client == null) throw new ErrorMessages("Le client entré n'existe pas.", HttpStatus.NOT_FOUND);
            if(client.getPanier() == null) throw new ErrorMessages("Une erreur sur le panier.", HttpStatus.NOT_FOUND);
            if(client.getPanier().getPanieritems() == null) throw new ErrorMessages("Une erreur sur le panier.", HttpStatus.NOT_FOUND);

            commande = new Commande();
            commande = commandeRepository.save(commande);
            commande.setStatut(CommandeStatut.INITIEE);
            commande.setDatecommande(new Date());
            commande.setUpdateat(new Date());
            commande.setMontantcommande(client.getPanier().getMontanttotal());
            commande.setNetapayer(client.getPanier().getMontanttotal());
            commande.setClient(client);
            commande.setReferenceclient(client.getReference());
            commande.setAccepted(false);
            commande.setNomclient(client.getFullname());
            commande.setReferencecommande(GenerateRandom.randomString(8));
            for (Panieritem item : client.getPanier().getPanieritems()) {
                item.setCommandeitem(commande);
                item.setPanier(null);
            }
            if (commandeDTO.getFraislivraison()!=0){
                commande.setFraislivraison(commandeDTO.getFraislivraison());
                commande.setStatut(CommandeStatut.ACCEPTEE);
            }
            //Définition de l'adresse de la commande
            Adresse adresse = adresseRepository.save(commandeDTO.getAdresse());
            commande.setAdresse(adresse);

            return commande;
        } else {
            return null;
        }
    }

    @Override
    public Commande getCommandesByreference(String referencecommande) {
        return commandeRepository.findByReferencecommande(referencecommande);
    }

    @Override
    public List<Commande> getCommandesByClient(String fullname) {
        return commandeRepository.findAllByNomclient(fullname);
    }

    @Override
    public List<Commande> getCommandesByReferenceClient(String referenceclient) {
        return commandeRepository.findAllByReferenceclientOrderByDatecommandeDesc(referenceclient);
    }

    @Override
    public List<Commande> getCommandesEncoursByClient(String fullname) {
        return commandeRepository.findAllByNomclientEncours(fullname);
    }

    @Override
    public List<Commande> getCommandesByAfromama(String name) {
        return null;
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public List<Commande> getAllCommandesEncours() {
        return commandeRepository.findAllByEncours();
    }

    @Override
    public Commande AcceptCommand(CommandeDTO commandeDTO) {
        Commande commande = commandeRepository.findByReferencecommande(commandeDTO.getReferencecommande());
        if(commande==null) throw new ErrorMessages("La commande que vous souhaitez y accéder est introuvable.", HttpStatus.NOT_FOUND);

        commande.setAccepted(Boolean.TRUE);
        commande.setUpdateat(new Date());
        commande.setStatut(CommandeStatut.ACCEPTEE);
        commande.setFraislivraison(commandeDTO.getFraislivraison());
        commande.setNetapayer(commandeDTO.getFraislivraison() + commandeDTO.getMontantcommande());
        for (Panieritem item : commande.getPanieritems()) {
            Commandepartenaire commandepartenaire =new Commandepartenaire();
            commandepartenaire = daoCommandePartenaire.save(commandepartenaire);
            commandepartenaire.setAfromama(item.getArticle().getRestaurant().getAfromama());
            commandepartenaire.setReferenceafromama(item.getArticle().getRestaurant().getAfromama().getReference());
            commandepartenaire.setNombredeplat(item.getQuantite());
            commandepartenaire.setMontant(item.getMontant());
            commandepartenaire.setDaterecuperation(commande.getAdresse().getDatedelivraison());
            commandepartenaire.setPlat((Plat) item.getArticle());
            commandepartenaire.setReferencecommande(commande.getReferencecommande());
            commandepartenaire.setReference(GenerateRandom.randomString(8));
            commandepartenaire.setNomclient(commande.getClient().getFullname());
            commandepartenaire.setCityaddress(commande.getAdresse().getCityaddress());
            commandepartenaire.setStreetaddress(commande.getAdresse().getStreetaddress());
            commandepartenaire.setStreetnumber(commande.getAdresse().getStreetnumber());
            commandepartenaire.setPhoneaddress(commande.getAdresse().getPhoneaddress());
            commandepartenaire.setLattitude(commande.getAdresse().getLattitude());
            commandepartenaire.setLongiture(commande.getAdresse().getLongiture());
            daoCommandePartenaire.save(commandepartenaire);
        }
        return commande;
    }

    @Override
    public Commande RejectCommand(CommandeDTO commandeDTO) {
        Commande commande = commandeRepository.findByReferencecommande(commandeDTO.getReferencecommande());
        if(commande==null) throw new ErrorMessages("La commande que vous souhaitez y accéder est introuvable.", HttpStatus.NOT_FOUND);

        commande.setAccepted(Boolean.FALSE);
        commande.setUpdateat(new Date());
        commande.setStatut(CommandeStatut.REJETEE);
        return commandeRepository.save(commande);    }

    @Override
    public Commande LivreCommand(CommandeDTO commandeDTO) {
        return null;
    }

    @Override
    public List<Commandepartenaire> findByReferenceafromama(String referenceAfromama) {
        return daoCommandePartenaire.findByReferenceafromama(referenceAfromama);
    }

    @Override
    public List<Commandepartenaire> findByReferencecommande(String referenceCommande) {
        return daoCommandePartenaire.findByReferencecommande(referenceCommande);
    }

    @Override
    public Commandepartenaire findByReference(String reference) {
        return daoCommandePartenaire.findByReference(reference);
    }

    @Override
    public Commandepartenaire terminerCommandeAfromama(String referencecommandepartenaire) {
        Commandepartenaire commandepartenaire = daoCommandePartenaire.findByReference(referencecommandepartenaire);
        if (commandepartenaire==null)throw new ErrorMessages("Ce plat n'est pas dans le systeme ",HttpStatus.CONFLICT);
       Commande commande=commandeRepository.findByReferencecommande(commandepartenaire.getReferencecommande());
       if (commande==null)throw new ErrorMessages("La commande de ce plat n'est plus dans le systeme ",HttpStatus.CONFLICT);
        if (commandepartenaire.isEtatpreparation()){//si le plat est deja prête
            if (commandepartenaire.getEtatlivraison().equals(LivraisonStatus.LIVRER))throw new ErrorMessages("Ce plat a deja été livré ",HttpStatus.CONFLICT);
            commande.setStatut(CommandeStatut.EN_PREPARATION);
        }else {
            commande.setStatut(CommandeStatut.ENATTENTELIVRAISON);
        }
        commandepartenaire.setEtatpreparation(!commandepartenaire.isEtatpreparation());
        return commandepartenaire;
    }

    @Override
    public List<Commandepartenaire> findByReferencelivreur(String referenceLivreur) {
        return daoCommandePartenaire.findByReferencelivreur(referenceLivreur);
    }

    @Override
    public List<Commandepartenaire> findAllcommandeTerminer() {
        return daoCommandePartenaire.findByEtatpreparationIsTrue();
    }

    @Override
    public Commandepartenaire associete_livreurTo_commande(String referencecommandepartenaire, String reference_livreur) {
        Commandepartenaire commandepartenaire = daoCommandePartenaire.findByReference(referencecommandepartenaire);
       if (commandepartenaire ==null)throw new ErrorMessages("Ce plat n est pas dans le systeme",HttpStatus.CONFLICT);
        DriverAfro driverAfro= driverAfroRepository.findByReference(reference_livreur);
        if (driverAfro==null)throw new ErrorMessages("Ce livreur  n est pas dans le systeme",HttpStatus.CONFLICT);
        if (commandepartenaire.getDriverAfro()==null){
            commandepartenaire.setReferencelivreur(driverAfro.getReference());
            commandepartenaire.setDriverAfro(driverAfro);
            commandepartenaire.setEtatlivraison(LivraisonStatus.NON_LIVRER);
        }else {
            if (commandepartenaire.getEtatlivraison().equals(LivraisonStatus.LIVRER))
                throw new ErrorMessages("Cette commande a déjà été livrée",HttpStatus.BAD_REQUEST);
            commandepartenaire.setEtatlivraison(LivraisonStatus.NON_LIVRER);
            commandepartenaire.setReferencelivreur(null);
            commandepartenaire.setDriverAfro(null);
        }

        return commandepartenaire;
    }

    @Override
    public boolean getEtatPayement(String referencecommande) {
        Commande commande= commandeRepository.findByReferencecommande(referencecommande);
        if (commande.getPaymentAfroeats()!=null && commande.getPaymentAfroeats().getState().equals("Payer")){
            return true;
        }else
        return false;
    }

    @Override
    public Commandepartenaire changeEtalivraison(CloturePlatDto cloturePlatDto) {

        if (!LivraisonStatus.REJETEE.equals(cloturePlatDto.getEtat_livraison()) &&
                !LivraisonStatus.LIVRER.equals(cloturePlatDto.getEtat_livraison()) ) throw new ErrorMessages("Bien vouloir choisir l'état de livraison valide",HttpStatus.BAD_REQUEST);
        DriverAfro driverAfro=driverAfroRepository.findByReference(cloturePlatDto.getReferencelivreur());
        if (driverAfro==null)throw new ErrorMessages("Le livreur n 'est pas dans le systeme",HttpStatus.BAD_REQUEST);
        Commandepartenaire commandepartenaire=daoCommandePartenaire.findByReference(cloturePlatDto.getReferencecommandpartenaire());
        if (commandepartenaire==null)throw new ErrorMessages("Le plat n 'est pas dans le systeme",HttpStatus.BAD_REQUEST);
        if (driverAfro.getCommandepartenaires().contains(commandepartenaire)){
            if (LivraisonStatus.REJETEE.equals(cloturePlatDto.getEtat_livraison())){//ici nous testons pour voir si le livreur a décliné la livraison
                commandepartenaire.setReferencelivreur(null);
                commandepartenaire.setDriverAfro(null);
            }else if (LivraisonStatus.LIVRER.equals(cloturePlatDto.getEtat_livraison())){
                Commande commande=commandeRepository.findByReferencecommande(cloturePlatDto.getReferencecommande());
                int nbr_livrairer=0;
                if (!commande.getCodecloture().equals(cloturePlatDto.getCodecloture()))//
                    throw new ErrorMessages("Le code cloture n'est pas valide",HttpStatus.BAD_REQUEST);

                for (Panieritem panieritem : commande.getPanieritems()) {
                    if (panieritem.getArticle().getReference().equals(commandepartenaire.getPlat().getReference())){
                        panieritem.setStatuslivraison(LivraisonStatus.LIVRER);
                    }

                    if (panieritem.getStatuslivraison()!=null && panieritem.getStatuslivraison().equals(LivraisonStatus.LIVRER)){
                        nbr_livrairer+=1;
                    }
                }
                if (nbr_livrairer==commande.getPanieritems().size()){
                    commande.setStatut(CommandeStatut.LIVREE);
                }
            }
            commandepartenaire.setEtatlivraison(cloturePlatDto.getEtat_livraison());
            return commandepartenaire;
        }else throw new ErrorMessages("Ce plat ne vous êtes  pas attribué",HttpStatus.BAD_REQUEST);
    }

    @Override
    public DashboardCommande getdashboard() {
        DashboardCommande dashboardCommande=new DashboardCommande();
        dashboardCommande.setNbrcommandelivreur(daoCommandePartenaire.countAllByReferencelivreurIsNotNull());
        dashboardCommande.setNbrcommandeafromama(daoCommandePartenaire.countAllByReferenceafromamaIsNotNull());
        dashboardCommande.setNbrttcommande(daoCommandePartenaire.countAllByReferenceIsNotNull());
        return dashboardCommande;
    }

    @Override
    public List<Commandepartenaire> findByReferencelivreurAndEtatlivraison(String referenceLivreur, String etat_livraison) {
        return daoCommandePartenaire.findByReferencelivreurAndEtatlivraison(referenceLivreur, etat_livraison);
    }

    @Override
    public List<CmdLivreurDTO> findCmdByReferencelivreur(String referenceliveur,String etatlivraison) {
        List<Commandepartenaire> cmdP ;
        if (etatlivraison==null){
            cmdP = findByReferencelivreur(referenceliveur);
        }else {
            cmdP=findByReferencelivreurAndEtatlivraison(referenceliveur, etatlivraison);
        }

        if(cmdP==null) return null;
        List<CmdLivreurDTO> cmdList = new ArrayList<>();
        cmdP.forEach(c->{
            CmdLivreurDTO cmdLivreurDTO = checkCmdLivreurDTO(cmdList, c.getReferencecommande());
            if(cmdLivreurDTO!=null){
                int i = cmdList.indexOf(cmdLivreurDTO);
                cmdList.get(i).getCommandepartenaires().add(c);
            } else {
                cmdLivreurDTO = new CmdLivreurDTO();
                cmdLivreurDTO.setReferencecommande(c.getReferencecommande());
                cmdLivreurDTO.setPhoto_client(appUserMetier.getPhotoClientByName(c.getNomclient()));
                cmdLivreurDTO.getCommandepartenaires().add(c);
                cmdList.add(cmdLivreurDTO);
            }
        });
        return cmdList;
    }

    CmdLivreurDTO checkCmdLivreurDTO(List<CmdLivreurDTO> cmdLivreurDTOS, String referencecommande){
        final CmdLivreurDTO[] cldto = new CmdLivreurDTO[1];
        cmdLivreurDTOS.forEach(cl->{
            if(cl.getReferencecommande().equals(referencecommande)){
                cldto[0] = cl;
            }
        });
        return cldto[0];
    }
}

