package com.iconsoft.afroeats.GestionUtilisateurs.Metier;

import com.iconsoft.afroeats.GestionUtilisateurs.DTO.AppUserDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.RegisterDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppUser;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.ClientAfro;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.DriverAfro;

import java.util.List;

public interface AppUserMetier {
    AppUser getUserByLogin(String login);
    AppUserDTO saveClient(RegisterDTO registerDTO);
    AppUserDTO saveAfromama(RegisterDTO registerDTO);
    AppUserDTO saveLivreur(RegisterDTO registerDTO);
    AppUserDTO confirm_register(String reference_user);
    AppUser saveAdmin(AppUserDTO appUserDTO);
    AppUserDTO findUserByIdUser(Long iduser);
    Afromama getByname(String name);
    List<AppUserDTO> getAllDrivers();
    List<AppUserDTO> getAllAfromamas();
    List<AppUserDTO> getAllClients();
    AppUserDTO findByReferenceAfromama(String reference);
    AppUserDTO findByreferenceRestaurant(String reference);
    boolean setDisponibilite(String reference);
    AppUserDTO updateProfil(AppUserDTO appUser);
    DriverAfro finddriverByReference(String reference);
    AppUser findUserByReference(String reference);
    AppUserDTO findClientByReference(String referenceClient);
    String getPhotoClientByName(String name);
}
