package com.iconsoft.afroeats.GestionUtilisateurs.WebRestController;

import com.iconsoft.afroeats.GestionUtilisateurs.DTO.*;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AccountMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppUserMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.DriverAfro;
import com.iconsoft.afroeats.GestionUtilisateurs.Services.AppUserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("account")
public class AccountRest {
    @Autowired
    AccountMetier accountMetier;
    @Autowired
    AppUserMetier appUserMetier;
    @PostMapping("signup")
    AppUserDTO registerClient(@RequestBody RegisterDTO registerDTO) {
        return accountMetier.registerClient(registerDTO);
    }

    @PostMapping("signupdriver")
    AppUserDTO registerDriver(@RequestBody RegisterDTO registerDTO) {
        return accountMetier.registerDriver(registerDTO);
    }

    @PostMapping("change_password")
    String changePassword(@RequestBody ChangePassword changePassword) throws JSONException {
        return accountMetier.changePassword(changePassword);
    }

    @PostMapping("signin")
    AppUserDTO loginAccount(@RequestBody LoginDTO loginDTO) {
        return accountMetier.loginAccount(loginDTO);
    }

    @PutMapping("update/doccument")
    public AppUserDTO confirmeidentity(@RequestBody DocumentCompleter documentCompleter){
        return accountMetier.confirmeidentity(documentCompleter);
    }

    @GetMapping("findlivreurbyreference/{referencelivreur}")
    AppUserDTO finddriverByReference(@PathVariable String referencelivreur){

        return AppUserService.permuteAppUserToAppUserDTO(appUserMetier.finddriverByReference(referencelivreur));
    }
    @GetMapping("findafromamabyreference/{referenceafromama}")
    AppUserDTO findafromamaByReference(@PathVariable String referenceafromama){
        return appUserMetier.findByReferenceAfromama(referenceafromama);
    }
}
