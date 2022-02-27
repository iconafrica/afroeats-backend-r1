package com.iconsoft.afroeats.GestionUtilisateurs.Services;

import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.AppRoleDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.AppUserDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppRoleMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppUserMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppRole;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppUser;
import com.iconsoft.afroeats.GestionUtilisateurs.Repository.AppRoleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppRoleService implements AppRoleMetier {
    final
    AppRoleRepository appRoleRepository;
    final
    AppUserMetier appUserMetier;

    public AppRoleService(AppRoleRepository appRoleRepository,@Lazy AppUserMetier appUserMetier) {
        this.appRoleRepository = appRoleRepository;
        this.appUserMetier = appUserMetier;
    }

    @Override
    public AppRole saveRole(AppRoleDTO appRoleDTO) {
        AppRole role = appRoleRepository.findByRolename(appRoleDTO.getRolename());
        if(role==null){
            return appRoleRepository.save(new AppRole(appRoleDTO.getRolename()));
        } else
            throw new ErrorMessages("Le role que vous essayer d'enregistrer existe deja.", HttpStatus.CONFLICT);
    }

    @Override
    public AppRole updateRole(AppRoleDTO appRoleDTO) {
        AppRole role = appRoleRepository.findByIdrole(appRoleDTO.getIdrole());
        if(role!=null){
            role.setRolename(appRoleDTO.getRolename());
            return appRoleRepository.save(role);
        } else {
            throw new ErrorMessages("Le role que vous essayer de mettre a jour n'existe deja.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public AppRole updateRoleByRolename(String rolename) {
        AppRole role = appRoleRepository.findByRolename(rolename);
        if(role!=null){
            role.setRolename(rolename);
            return appRoleRepository.save(role);
        } else {
            throw new ErrorMessages("Le role que vous essayer de mettre a jour n'existe deja.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public AppRole getRole(String rolename) {
        return appRoleRepository.findByRolename(rolename);
    }

    @Override
    public List<AppRole> getRoles() {
        return appRoleRepository.findAll();
    }

    @Override
    public boolean addRoleToUser(Long iduser, String rolename) {
        AppUserDTO user = appUserMetier.findUserByIdUser(iduser);
        if(user!=null){
            AppRole role = appRoleRepository.findByRolename(rolename);
            if(role!=null){
                user.getRoles().add(role);
                return true;
            } else
                throw new ErrorMessages("Impossible d'affecter ce role car il n'existe pas.", HttpStatus.NOT_FOUND);
        } else
            throw new ErrorMessages("Impossible d'affecter ce role car l'utilisateur n'existe pas.", HttpStatus.NOT_FOUND);
    }

    @Override
    public boolean deleteRoleToUser(Long iduser) {
        AppUserDTO vendeur = appUserMetier.findUserByIdUser(iduser);
        if(vendeur!=null){
            vendeur.getRoles().clear();
            return this.addRoleToUser(iduser, "CLIENT");
        } else
            throw new ErrorMessages("Impossible d'affecter ce role car l'utilisateur n'existe pas.", HttpStatus.NOT_FOUND);
    }
}
