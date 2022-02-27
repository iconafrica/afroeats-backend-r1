package com.iconsoft.afroeats.GestionLivraison.WebRestController;

import com.iconsoft.afroeats.GestionLivraison.Metier.MetierFraisLivraison;
import com.iconsoft.afroeats.GestionLivraison.Model.Arrondissement;
import com.iconsoft.afroeats.GestionLivraison.Model.RequetFraisLivraison;
import com.iconsoft.afroeats.GestionLivraison.Model.ResponseFraisLivraison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("livraison")
public class FraisLivraisonRest {

    @Autowired
    MetierFraisLivraison metierFraisLivraison;

    @PostMapping("get_frais")
    ResponseFraisLivraison getFraisLivraison(@RequestBody RequetFraisLivraison requetFraisLivraison){
        return metierFraisLivraison.fraislivraion(requetFraisLivraison);
    }

    @GetMapping("adresse")
    List<Arrondissement> getAddresse(){
        return metierFraisLivraison.getAllArrondissement();
    }
}
