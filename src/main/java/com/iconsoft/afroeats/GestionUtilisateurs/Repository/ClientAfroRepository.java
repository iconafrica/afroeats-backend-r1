package com.iconsoft.afroeats.GestionUtilisateurs.Repository;

import com.iconsoft.afroeats.GestionUtilisateurs.Models.ClientAfro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAfroRepository extends JpaRepository<ClientAfro, Long> {
    ClientAfro findByFullname(String name);
    ClientAfro findByReference(String reference);
}
