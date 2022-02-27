package com.iconsoft.afroeats.GestionPanier.Repository;

import com.iconsoft.afroeats.GestionPanier.Models.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
    Panier findByIdpanier(Long idpanier);
    Panier findByReference(String reference);
}
