package com.iconsoft.afroeats.GestionLivraison.Metier;

import com.iconsoft.afroeats.GestionLivraison.Model.Arrondissement;
import com.iconsoft.afroeats.GestionLivraison.Model.RequetFraisLivraison;
import com.iconsoft.afroeats.GestionLivraison.Model.ResponseFraisLivraison;

import java.util.List;

public interface MetierFraisLivraison {
    ResponseFraisLivraison fraislivraion(RequetFraisLivraison requetFraisLivraison);
    List<Arrondissement> getAllArrondissement();
}
