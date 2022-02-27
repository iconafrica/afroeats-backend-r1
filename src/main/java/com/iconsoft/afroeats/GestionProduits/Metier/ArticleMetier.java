package com.iconsoft.afroeats.GestionProduits.Metier;

import com.iconsoft.afroeats.GestionProduits.DTO.ArticleDTO;
import com.iconsoft.afroeats.GestionProduits.DTO.PlatJourDto;
import com.iconsoft.afroeats.GestionProduits.Models.Article;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ArticleMetier {
    Article manageArticle(ArticleDTO articleDTO);
    List<ArticleDTO> getAllPlatsIsActive();
    List<ArticleDTO> getAllPlatsIsActiveByReferenceRestaurant(String referencerestaurant);
    void deletePlanningItem(Long idplaningitem);
    Article getArticleByReferenceArticle(String referenceArticle);
    PlatJourDto getPlatByReferencearticleAndJour(String referencearticle,String jour);
    List<Article> getAllarticle();
}
