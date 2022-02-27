package com.iconsoft.afroeats.GestionUtilisateurs.Repository;

import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AfroMamaRepository extends JpaRepository<Afromama, Long> {
    Afromama findByFullname(String name);
    Afromama findByReference(String reference);
    @Query("select u from Afromama u where u.restaurant.reference = :reference  and u.status='Actif'")
    Afromama findByreferenceRestaurant(String reference);
}
