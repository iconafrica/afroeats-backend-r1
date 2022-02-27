package com.iconsoft.afroeats.GestionProduits.Repository;

import com.iconsoft.afroeats.GestionProduits.Models.Menus;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenusRepository extends JpaRepository<Menus, Long> {
    List<Menus> findAllByJour(String jour);
    Page<Menus> findAllByOrderByNumeroAsc(Pageable pageable);
    List<Menus> getAllByOrderByNumeroAsc();
    List<Menus> findByReferencerestaurant(String reference);
    Menus findByReference(String reference);
    @Query("select distinct m.restaurant from Menus m where m.plats.size<>0")
    List<Restaurant> findAllMenuWithPlat();
}
