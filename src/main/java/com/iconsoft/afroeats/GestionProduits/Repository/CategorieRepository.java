package com.iconsoft.afroeats.GestionProduits.Repository;

import com.iconsoft.afroeats.GestionProduits.Models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie findByNomcategorie(String nom);
    Categorie findByIdcategorie(Long idcategorie);
}
