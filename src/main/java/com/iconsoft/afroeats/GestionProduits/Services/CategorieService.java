package com.iconsoft.afroeats.GestionProduits.Services;

import com.iconsoft.afroeats.Cloudinary.Services.ServiceCloudinary;
import com.iconsoft.afroeats.Configuration.GenerateRandom;
import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionProduits.DTO.CategorieDTO;
import com.iconsoft.afroeats.GestionProduits.Metier.CategorieMetier;
import com.iconsoft.afroeats.GestionProduits.Models.Categorie;
import com.iconsoft.afroeats.GestionProduits.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CategorieService implements CategorieMetier {

    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    ServiceCloudinary serviceCloudinary;
    @Override
    public Categorie saveCategorie(CategorieDTO categorieDTO) {
        Categorie cat = categorieRepository.findByNomcategorie(categorieDTO.getNomcategorie());
        if(cat!=null) throw new ErrorMessages("Impossible de créer cette catégorie car elle existe déjà.", HttpStatus.CONFLICT);
        cat = new Categorie();
        categorieRepository.save(cat);
        cat.setNomcategorie(categorieDTO.getNomcategorie());
        cat.setReference(GenerateRandom.randomString(8));
        cat.setImagecategorie( serviceCloudinary.saveObjectOnCloudinary(categorieDTO.getImagecategorie(),"categorie",
                "categorie",cat.getReference()));
        cat.setCreatedat(new Date());
        cat.setUpdateat(new Date());

        return cat;
    }

    @Override
    public Categorie updateCategorie(CategorieDTO categorieDTO) {
        Categorie cat = categorieRepository.findByIdcategorie(categorieDTO.getIdcategorie());
        if(cat==null) throw new ErrorMessages("Impossible de modifier cette catégorie car elle n'existe pas.", HttpStatus.NOT_FOUND);

        cat.setNomcategorie(categorieDTO.getNomcategorie());
        if (!categorieDTO.getImagecategorie().contains("cloudinary.com/icon-soft")){
            cat.setImagecategorie(serviceCloudinary.saveObjectOnCloudinary(categorieDTO.getImagecategorie(),"categorie",
                    "categorie",cat.getReference()));
        }
        return cat;
    }

    @Override
    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getCategorieByName(String nomcategorie) {
        return categorieRepository.findByNomcategorie(nomcategorie);
    }
}
