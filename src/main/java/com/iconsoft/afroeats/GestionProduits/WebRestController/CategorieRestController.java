package com.iconsoft.afroeats.GestionProduits.WebRestController;

import com.iconsoft.afroeats.GestionProduits.DTO.CategorieDTO;
import com.iconsoft.afroeats.GestionProduits.Metier.CategorieMetier;
import com.iconsoft.afroeats.GestionProduits.Models.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("categorie")
public class CategorieRestController {
    @Autowired
    CategorieMetier categorieMetier;

    @PostMapping("create")
    Categorie saveCategorie(@RequestBody CategorieDTO categorieDTO){
        return categorieMetier.saveCategorie(categorieDTO);
    }

    @PutMapping("update")
    Categorie updateCategorie(@RequestBody CategorieDTO categorieDTO){
        return categorieMetier.updateCategorie(categorieDTO);
    }

    @GetMapping("getall")
    List<Categorie> getAllCategorie(){
        return categorieMetier.getAllCategorie();
    }
}
