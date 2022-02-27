package com.iconsoft.afroeats.GestionProduits.Repository;

import com.iconsoft.afroeats.GestionProduits.Models.Plattiming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlattimingRepository extends JpaRepository<Plattiming, Long> {
}
