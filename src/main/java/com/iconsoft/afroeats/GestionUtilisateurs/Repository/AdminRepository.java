package com.iconsoft.afroeats.GestionUtilisateurs.Repository;

import com.iconsoft.afroeats.GestionUtilisateurs.Models.Adminafro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Adminafro, Long> {
}
