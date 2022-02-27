package com.iconsoft.afroeats.GestionProduits.Services;

import com.iconsoft.afroeats.Cloudinary.Services.ServiceCloudinary;
import com.iconsoft.afroeats.Configuration.GenerateRandom;
import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionProduits.DTO.ArticleDTO;
import com.iconsoft.afroeats.GestionProduits.DTO.PlatJourDto;
import com.iconsoft.afroeats.GestionProduits.Metier.ArticleMetier;
import com.iconsoft.afroeats.GestionProduits.Metier.CategorieMetier;
import com.iconsoft.afroeats.GestionProduits.Models.*;
import com.iconsoft.afroeats.GestionProduits.Repository.*;
import com.iconsoft.afroeats.GestionRestaurant.Dao.RestaurantDao;
import com.iconsoft.afroeats.GestionRestaurant.Metier.PointVenteMetier;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppUserMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArticleService implements ArticleMetier {

    @Autowired
    CategorieMetier categorieMetier;
    @Autowired
    PlatRepository platRepository;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    AppUserMetier appUserMetier;
    @Autowired
    PlattimingRepository plattimingRepository;
    @Autowired
    PlattimingitemRepository plattimingitemRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    PointVenteMetier pointVenteMetier;
    @Autowired
    ServiceCloudinary serviceCloudinary;



    @Override
    public Article manageArticle(ArticleDTO articleDTO) {
        Categorie cat = categorieMetier.getCategorieByName(articleDTO.getNomcategorie());
        if(cat==null) throw new ErrorMessages("Categorie inexistante, l'operation ne peut se poursuivre.", HttpStatus.NOT_FOUND);

        Produit produit;
        Plat plat;

        if(articleDTO.getAction().equalsIgnoreCase("create")){
            if(articleDTO.getTypearticle().equalsIgnoreCase("plat")){
                plat = new Plat();
                BeanUtils.copyProperties(articleDTO, plat);

                Restaurant restaurant = (Restaurant) pointVenteMetier.findPointDeVenteByReference(articleDTO.getReference_restaurant());
                if(restaurant==null) throw new ErrorMessages("Le restaurant n'existe pas.", HttpStatus.NOT_FOUND);
                Plat testPlat = platRepository.findPlatByRestaurantAndNomarticle(restaurant, articleDTO.getNomarticle());
                if(testPlat!=null) throw new ErrorMessages("Cette Afromama possede déja un plat de ce nom", HttpStatus.CONFLICT);
                plat.setRestaurant(restaurant);
                plat.setCategorie(cat);
                plat.setNomcategorie(cat.getNomcategorie());
                plat.setTypearticle("plat");
                plat.setReference(GenerateRandom.randomString(8));
                plat.setCreatedat(new Date());
                plat.setUpdateat(new Date());
                plat.setStatus(true);

                plat = platRepository.save(plat);
                plat.setImagearticle(
                        serviceCloudinary.saveObjectOnCloudinary(plat.getImagearticle(),"articles",
                        "plat",plat.getReference()));
                return plat;
            } else if(articleDTO.getTypearticle().equalsIgnoreCase("produit")){
                //En attente
            }
        } else if(articleDTO.getAction().equalsIgnoreCase("update")){
            if(articleDTO.getTypearticle().equalsIgnoreCase("plat")){
                Plat plat1=platRepository.findByIdarticle(articleDTO.getIdarticle());
                plat1.setCategorie(articleDTO.getCategorie());
                plat1.setImagearticle(articleDTO.getImagearticle());
                plat1.setDescriptionarticle(articleDTO.getDescriptionarticle());
                plat1.setPrixarticle(articleDTO.getPrixarticle());
                if (!articleDTO.getImagearticle().contains("cloudinary.com/icon-soft")){
                    plat1.setImagearticle(serviceCloudinary.saveObjectOnCloudinary(articleDTO.getImagearticle(),"articles",
                            "plat",plat1.getReference()));
                }


            } else if(articleDTO.getTypearticle().equalsIgnoreCase("produit")){
                //En attente
            }
        } else if(articleDTO.getAction().equalsIgnoreCase("delete")){
            //En attente
        }
        return null;
    }

    @Override
    public List<ArticleDTO> getAllPlatsIsActive() {
        return permutePlatListToArticleDTOList(platRepository.findAllByStatusIsTrue());
    }

    @Override
    public List<ArticleDTO> getAllPlatsIsActiveByReferenceRestaurant(String referencerestaurant) {
        return permutePlatListToArticleDTOList(platRepository.findAllByStatusIsTruereferenceAfro(referencerestaurant));
    }

    @Override
    public void deletePlanningItem(Long idplaningitem) {
        Plattimingitem plattimingitem= plattimingitemRepository.getById(idplaningitem);
        plattimingitem.setPlattiming(null);
        plattimingitemRepository.deleteById(idplaningitem);
    }

    @Override
    public Article getArticleByReferenceArticle(String referenceArticle) {
        return articleRepository.findByReference(referenceArticle);
    }

    @Override
    public PlatJourDto getPlatByReferencearticleAndJour(String referencearticle, String jour) {
        return null;
    }

    @Override
    public List<Article> getAllarticle() {
        List<Article>  articles=articleRepository.findAll();
        return articles;
    }

    static ArticleDTO permuteArticleToArticleDTO(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);
        return articleDTO;
    }

    static List<ArticleDTO> permuteArticleListToArticleDTOList(List<Article> articles){
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        articles.forEach(r->{
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(r, articleDTO);
            articleDTOList.add(articleDTO);
        });
        return articleDTOList;
    }

    static List<ArticleDTO> permutePlatListToArticleDTOList(List<Plat> plats){
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        plats.forEach(r->{
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(r, articleDTO);
            articleDTOList.add(articleDTO);
        });
        return articleDTOList;
    }
}
