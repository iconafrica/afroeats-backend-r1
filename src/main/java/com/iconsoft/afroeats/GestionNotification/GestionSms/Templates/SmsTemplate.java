package com.iconsoft.afroeats.GestionNotification.GestionSms.Templates;

import com.iconsoft.afroeats.GestionCommande.Models.Commande;

public class SmsTemplate {

    public static String inscriptionClient(String name){
        return  name +", nous sommes content de vous compter parmis nos client. Vous offrir un service de qualité tel est notre mission.";
    }

    public static String activationComptePartenaire(String name){
        return name + ", votre compte a bien été activé par les administrateurs. Connecter vous afin de découvrir l'ensemble des fonctionnalités. N'hésitez pas de nous contacter en cas de difficulté. Merci!";
    }

    public static String commandePretePourLivraison(Commande commande){
        return commande.getNomclient() + ", votre commande " + commande.getReferencecommande() + " effectuée le " + commande.getDatecommande() + " est prête. Vous serez livré sous peu. Merci pour votre confiance.";
    }

    public static String commandeCoteAfromama(String reference, String name){
        return name + ", la commande " + reference + " vient d'être effectuée. Connectez vous afin d'obtenir plus d'informations. Merci!";
    }

    public static String commandeCoteLivreur(Commande commande, String name){
        return name + ", la commande " + commande.getReferencecommande() +" est prête et doit être livrée le " + commande.getAdresse().getDatedelivraison() + ". Vous avez été choisi(e) pour effectuer la livraison. Connectez vous afin d'obtenir plus d'informations.";
    }
}
