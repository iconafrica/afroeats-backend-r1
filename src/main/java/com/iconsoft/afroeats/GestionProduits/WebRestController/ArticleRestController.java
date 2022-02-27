package com.iconsoft.afroeats.GestionProduits.WebRestController;

import com.iconsoft.afroeats.GestionProduits.DTO.ArticleDTO;
import com.iconsoft.afroeats.GestionProduits.DTO.PlatJourDto;
import com.iconsoft.afroeats.GestionProduits.Metier.ArticleMetier;
import com.iconsoft.afroeats.GestionProduits.Models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/article")
public class ArticleRestController {
    @Autowired
    ArticleMetier articleMetier;

    @PostMapping("/managearticle")
    Article ManageArticle(@RequestBody ArticleDTO articleDTO){
        return articleMetier.manageArticle(articleDTO);
    }

    @GetMapping("/getplats")
    List<ArticleDTO> getAllPlats(){
        return articleMetier.getAllPlatsIsActive();
    }
    @GetMapping("/articles")
    List<Article> getAllArticle(){
        return articleMetier.getAllarticle();
    }

    @GetMapping("/getplat/{referencearticle}")
    Article getPlatByReferencearticle(@PathVariable String referencearticle){
        return articleMetier.getArticleByReferenceArticle(referencearticle);
    }

   /* @GetMapping("/getplat/{referencearticle}/{jour}")
    PlatJourDto getPlatByReferencearticleAndJour(@PathVariable String referencearticle,@PathVariable String jour){
        return articleMetier.getPlatByReferencearticleAndJour(referencearticle,jour);
    }*/


    @GetMapping("/getplats/{referencerestaurant}")
    List<ArticleDTO> getAllPlatsAfromama(@PathVariable String referencerestaurant){
        return articleMetier.getAllPlatsIsActiveByReferenceRestaurant(referencerestaurant);
    }
    @DeleteMapping("delete/planningitem/{idplaningitem}")
    void delete(@PathVariable Long idplaningitem){

        articleMetier.deletePlanningItem(idplaningitem);
    }

}
