package com.iconsoft.afroeats.GestionUtilisateurs.WebRestController;

import com.iconsoft.afroeats.GestionUtilisateurs.DTO.AppUserDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppUserMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.ClientAfro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class AppUserRest {
    @Autowired
    AppUserMetier appUserMetier;

    @GetMapping("getalldrivers")
    List<AppUserDTO> getAllDrivers(){
        return appUserMetier.getAllDrivers();
    }

    @GetMapping("getallafromamas")
    List<AppUserDTO> getAllAfromamas(){
        return appUserMetier.getAllAfromamas();
    }

    @GetMapping("getallclients")
    List<AppUserDTO> getAllClients(){
        return appUserMetier.getAllClients();
    }

    @GetMapping("confirme_regidter/{reference}")
    AppUserDTO confirme_regidter(@PathVariable String reference){
        return appUserMetier.confirm_register(reference);
    }
    @GetMapping("afromama/{reference}")
    AppUserDTO getAfromamaByRef(@PathVariable String reference){
        return appUserMetier.findByReferenceAfromama(reference);
    }


    @GetMapping("afromamabyrestaurant/{referencerestaurant}")
    AppUserDTO getAfromamaByReferenceRestaurant(@PathVariable String referencerestaurant){
        return appUserMetier.findByreferenceRestaurant(referencerestaurant);
    }
    @GetMapping("clientby/{referenceClient}")
    AppUserDTO findClientByReference(String referenceClient){
        return appUserMetier.findClientByReference(  referenceClient);
    }

    @GetMapping("disponibilite/{reference}")
    boolean setDisponibilite(@PathVariable String reference){
        return appUserMetier.setDisponibilite(reference);
    }

    @PutMapping("update/profil")
    AppUserDTO updateProfil(@RequestBody AppUserDTO appUser){
        return appUserMetier.updateProfil(appUser);
    }

}
