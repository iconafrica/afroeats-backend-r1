package com.iconsoft.afroeats.GestionRestaurant.Dao;

import com.iconsoft.afroeats.GestionRestaurant.Model.PointDeVente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointDeVenteDao extends JpaRepository<PointDeVente,Long> {
    PointDeVente findPointDeVenteByReference(String reference);
    PointDeVente findPointDeVenteByLibelle(String libelele);
}
