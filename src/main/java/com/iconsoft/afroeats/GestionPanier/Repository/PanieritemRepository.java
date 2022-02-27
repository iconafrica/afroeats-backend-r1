package com.iconsoft.afroeats.GestionPanier.Repository;

import com.iconsoft.afroeats.GestionPanier.Models.Panieritem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanieritemRepository extends JpaRepository<Panieritem, Long> {
    @Query("select p from Panieritem as p where p.panier.reference = ?1")
    List<Panieritem> getAllPanierItemByReferencePanier(@Param("x") String reference);
    Panieritem findByIdpanieritem(Long idpanieritem);
    @Query("select p from Panieritem p where p.panier.reference = ?1 and p.nomarticle = ?2")
    Panieritem getPanieritemByReferenceAndArticle(String reference, String nomarticle);

}

