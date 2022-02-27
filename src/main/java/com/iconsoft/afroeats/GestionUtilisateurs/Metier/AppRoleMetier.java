package com.iconsoft.afroeats.GestionUtilisateurs.Metier;


import com.iconsoft.afroeats.GestionUtilisateurs.DTO.AppRoleDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppRole;

import java.util.List;

public interface AppRoleMetier {
    AppRole saveRole(AppRoleDTO appRoleDTO);
    AppRole updateRole(AppRoleDTO appRoleDTO);
    AppRole updateRoleByRolename(String rolename);
    AppRole getRole(String rolename);
    List<AppRole> getRoles();
    boolean addRoleToUser(Long id_user, String rolename);
    boolean deleteRoleToUser(Long id_user);
}
