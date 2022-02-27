package com.iconsoft.afroeats.GestionProduits.Repository;

import com.iconsoft.afroeats.GestionProduits.Models.Plat;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {
    Plat findPlatByRestaurantAndNomarticle(Restaurant restaurant, String nomarticle);
    List<Plat> findAllByStatusIsTrue();
    Plat findByIdarticle(Long id);
    Plat findByReference(String reference);
    @Query("select p from plat p where p.restaurant.reference=:reference")
    List<Plat> findAllByStatusIsTruereferenceAfro(String reference);


}
