package com.iconsoft.afroeats.GestionRestaurant.Metier;

import com.iconsoft.afroeats.GestionRestaurant.Dto.PointVenteSaveDTO;
import com.iconsoft.afroeats.GestionRestaurant.Model.PointDeVente;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;

import java.util.List;

public interface PointVenteMetier {
    PointDeVente findPointDeVenteByReference(String reference);
    Restaurant findRestaurantByAndStatusIsTrue(String reference);
    Restaurant saveRestaurant(PointVenteSaveDTO pointVenteSaveDTO);
    Restaurant updateRestaurant(PointVenteSaveDTO pointVenteSaveDTO,String referencerestaurant);
    PointDeVente findPointDeVenteByLibelle(String libelele);
    List<PointDeVente> findAllPointDeVente();
    List<Restaurant> findAllPointDeVente_with_menu();
}
