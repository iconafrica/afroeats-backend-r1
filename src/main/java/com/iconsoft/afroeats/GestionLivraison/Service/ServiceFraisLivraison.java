package com.iconsoft.afroeats.GestionLivraison.Service;

import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionLivraison.Metier.MetierFraisLivraison;
import com.iconsoft.afroeats.GestionLivraison.Model.Arrondissement;
import com.iconsoft.afroeats.GestionLivraison.Model.RequetFraisLivraison;
import com.iconsoft.afroeats.GestionLivraison.Model.ResponseFraisLivraison;
import com.iconsoft.afroeats.GestionPanier.Metier.PanierMetier;
import com.iconsoft.afroeats.GestionPanier.Models.Panier;
import com.iconsoft.afroeats.GestionPanier.Models.Panieritem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceFraisLivraison implements MetierFraisLivraison {
    @Autowired
    PanierMetier panierMetier;
    @Override
    public ResponseFraisLivraison fraislivraion(RequetFraisLivraison requetFraisLivraison) {
        ResponseFraisLivraison responseFraisLivraison = new ResponseFraisLivraison();
        double MinCode = 75000;
        double MaxCode = 75020;
//cette methode verrifie si le panier est vide en suite vérrifier s il y a de élement dans le panier
        Panier  panier= panierMetier.findPanierByReference(requetFraisLivraison.getReferencepanier());
        if (panier==null)throw new ErrorMessages("Votre panier n'est pas dans notre systéme",HttpStatus.NOT_FOUND);
        if (panier.getPanieritems()==null)throw new ErrorMessages("Votre panier est vide donc impossible d'avoir les frais de livraison",HttpStatus.NOT_FOUND);
        // on parcour la liste des élement du panier pour verrifier si le codepostal de livraison est identique a celui de l'afromama
        for (Panieritem item : panier.getPanieritems()) {
// si le code postal n est pas celui de l'afromama on note le code dans une liste et par la suite on verifie si le code postal est deja dans la liste
            List<Integer> listecode=new ArrayList<Integer>();

            listecode.add(item.getArticle().getRestaurant().getAfromama().getAdresses().getCodepostal());
                if (requetFraisLivraison.getCodepostal() >= MinCode && requetFraisLivraison.getCodepostal()<= MaxCode) {
                   responseFraisLivraison.setFraislivraison(7);
                } else if (requetFraisLivraison.getCodepostal() >= 91 && requetFraisLivraison.getCodepostal() <= 95) {
                    responseFraisLivraison.setFraislivraison(10);
                } else
                    throw new ErrorMessages("Votre Code postal n'est pas pris en compte par le systéme", HttpStatus.NOT_FOUND);

            if (!listecode.contains(item.getArticle().getRestaurant().getAfromama().getAdresses().getCodepostal())){

                    if (requetFraisLivraison.getCodepostal() >= MinCode && requetFraisLivraison.getCodepostal()<= MaxCode) {
                        responseFraisLivraison.setFraislivraison(responseFraisLivraison.getFraislivraison()+7);
                    } else if (requetFraisLivraison.getCodepostal() >= 91 && requetFraisLivraison.getCodepostal() <= 95) {
                        responseFraisLivraison.setFraislivraison(responseFraisLivraison.getFraislivraison()+10);
                    } else
                        throw new ErrorMessages("Votre Code postal n'est pas pris en compte par le systéme", HttpStatus.NOT_FOUND);
                responseFraisLivraison.setRaison("les frais de livraison sont élevé par que vos plats se retrouve dans "+listecode.size()+" afromama");
            }

        }
 return responseFraisLivraison;
    }

    @Override
    public List<Arrondissement> getAllArrondissement() {
        Arrondissement arrondissement10 = new Arrondissement("Commune de Paris 10e Arrondissement", 75010);
        Arrondissement arrondissement5 = new Arrondissement("Commune de Paris 5e Arrondissement", 75005);
        Arrondissement arrondissement9 = new Arrondissement("Commune de Paris 9e Arrondissement", 75009);
        Arrondissement arrondissement7 = new Arrondissement("Commune de Paris 7e Arrondissement", 75007);
        Arrondissement arrondissement6 = new Arrondissement("Commune de Paris 6e Arrondissement", 75006);
        Arrondissement arrondissement8 = new Arrondissement("Commune de Paris 8e Arrondissement", 75008);
        Arrondissement arrondissement3 = new Arrondissement("Commune de Paris 3e Arrondissement", 75003);
        Arrondissement arrondissement4 = new Arrondissement("Commune de Paris 4e Arrondissement", 75004);
        Arrondissement arrondissement15 = new Arrondissement("Commune de Paris 15e Arrondissement", 75015);
        Arrondissement arrondissement0 = new Arrondissement("Commune de Paris ", 75000);
        Arrondissement arrondissement2 = new Arrondissement("Commune de Paris 2e Arrondissement", 75002);
        Arrondissement arrondissement18 = new Arrondissement("Commune de Paris 18e Arrondissement", 75018);
        Arrondissement arrondissement20 = new Arrondissement("Commune de Paris 20e Arrondissement", 75020);
        Arrondissement arrondissement19 = new Arrondissement("Commune de Paris 19e Arrondissement", 75019);
        Arrondissement arrondissement13 = new Arrondissement("Commune de Paris 13e Arrondissement", 75013);
        Arrondissement arrondissement17 = new Arrondissement("Commune de Paris 17e Arrondissement", 75017);
        Arrondissement arrondissement1 = new Arrondissement("Commune de Paris 1e Arrondissement", 75001);
        Arrondissement arrondissement16 = new Arrondissement("Commune de Paris 16e Arrondissement", 75016);
        Arrondissement arrondissement11 = new Arrondissement("Commune de Paris 11e Arrondissement", 75011);
        Arrondissement arrondissement12 = new Arrondissement("Commune de Paris 12e Arrondissement", 75012);
        Arrondissement arrondissement14 = new Arrondissement("Commune de Paris 14e Arrondissement", 75014);


        Arrondissement arrondissement91 = new Arrondissement("Commune de Paris 91e Arrondissement", 91);
        Arrondissement arrondissement92 = new Arrondissement("Commune de Paris 92e Arrondissement", 92);
        Arrondissement arrondissement93 = new Arrondissement("Commune de Paris 93e Arrondissement", 93);
        Arrondissement arrondissement94 = new Arrondissement("Commune de Paris 94e Arrondissement", 94);
        Arrondissement arrondissement95 = new Arrondissement("Commune de Paris 95e Arrondissement", 95);



        List<Arrondissement> arrondissements = new ArrayList<>();
        arrondissements.add(arrondissement0);
        arrondissements.add(arrondissement1);
        arrondissements.add(arrondissement2);
        arrondissements.add(arrondissement3);
        arrondissements.add(arrondissement4);
        arrondissements.add(arrondissement5);
        arrondissements.add(arrondissement6);
        arrondissements.add(arrondissement7);
        arrondissements.add(arrondissement8);
        arrondissements.add(arrondissement9);
        arrondissements.add(arrondissement10);
        arrondissements.add(arrondissement11);
        arrondissements.add(arrondissement12);
        arrondissements.add(arrondissement13);
        arrondissements.add(arrondissement14);
        arrondissements.add(arrondissement15);
        arrondissements.add(arrondissement16);
        arrondissements.add(arrondissement17);
        arrondissements.add(arrondissement18);
        arrondissements.add(arrondissement19);
        arrondissements.add(arrondissement20);

        arrondissements.add(arrondissement91);
        arrondissements.add(arrondissement92);
        arrondissements.add(arrondissement93);
        arrondissements.add(arrondissement94);
        arrondissements.add(arrondissement95);

        return arrondissements;
    }


}
