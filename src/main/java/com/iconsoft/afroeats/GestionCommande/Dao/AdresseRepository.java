package com.iconsoft.afroeats.GestionCommande.Dao;

import com.iconsoft.afroeats.GestionCommande.Models.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}
