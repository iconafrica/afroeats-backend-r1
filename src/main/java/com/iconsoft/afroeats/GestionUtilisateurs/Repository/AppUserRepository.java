package com.iconsoft.afroeats.GestionUtilisateurs.Repository;

import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /* Rechercher un utilisateur  par username, email ou phone selon le parametre entre
     *  Cette fonction est utile a la connexion */
    @Query("select u from AppUser u where (lower(u.email) = :login or u.mobilenumber =: login) and u.status='Actif'")
    AppUser findByEmailOrPhone(String login);
    AppUser findByEmail(String login);
    AppUser findByIduser(Long iduser);
    AppUser findByReference(String referenec);
    @Query("select c.photoprofile from AppUser c where c.fullname = ?1 ")
    String findPhotoByNom(@Param("x") String nom);
}
