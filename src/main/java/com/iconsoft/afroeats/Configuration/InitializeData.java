package com.iconsoft.afroeats.Configuration;

import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.AppRoleDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.AppUserDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppRoleMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppUserMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class InitializeData {

    @Autowired
    AppRoleMetier appRoleMetier;
    @Autowired
    AppUserMetier appUserMetier;

    @Bean
    void insert() {
        if (appRoleMetier.getRoles().size() == 0) {
            appRoleMetier.saveRole(new AppRoleDTO("ADMIN"));
            appRoleMetier.saveRole(new AppRoleDTO("DRIVER"));
            appRoleMetier.saveRole(new AppRoleDTO("FEEDER"));
            appRoleMetier.saveRole(new AppRoleDTO("CLIENT"));
            AppUser user = appUserMetier.saveAdmin(new AppUserDTO("rootafroeats", "icon.soft.vision@gmail.com", "653318949"));
            if (user == null) {
                throw new ErrorMessages("L'utilisateur n'a pas ete cree.", HttpStatus.BAD_REQUEST);
            }
        }
    }


}
