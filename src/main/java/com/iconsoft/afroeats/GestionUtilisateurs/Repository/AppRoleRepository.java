package com.iconsoft.afroeats.GestionUtilisateurs.Repository;

import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRolename(String rolename);
    AppRole findByIdrole(Long id);
}
