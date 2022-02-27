package com.iconsoft.afroeats.GestionProduits.Metier;

import com.iconsoft.afroeats.GestionProduits.DTO.CategorieDTO;
import com.iconsoft.afroeats.GestionProduits.Models.Categorie;
import java.util.List;

public interface CategorieMetier {
    Categorie saveCategorie(CategorieDTO categorieDTO);
    Categorie updateCategorie(CategorieDTO categorieDTO);
    List<Categorie> getAllCategorie();
    Categorie getCategorieByName(String nomcategorie);
}
