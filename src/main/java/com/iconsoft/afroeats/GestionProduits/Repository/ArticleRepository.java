package com.iconsoft.afroeats.GestionProduits.Repository;

import com.iconsoft.afroeats.GestionProduits.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findByReference(String reference);

}
