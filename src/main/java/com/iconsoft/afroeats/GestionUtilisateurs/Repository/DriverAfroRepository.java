package com.iconsoft.afroeats.GestionUtilisateurs.Repository;

import com.iconsoft.afroeats.GestionUtilisateurs.Models.DriverAfro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverAfroRepository extends JpaRepository<DriverAfro, Long> {
    DriverAfro findByReference(String reference);
}
