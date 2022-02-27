package com.iconsoft.afroeats.GestionUtilisateurs.Services;

import com.google.gson.Gson;
import com.iconsoft.afroeats.Cloudinary.Services.ServiceCloudinary;
import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionNotification.GestionEmail.Service.MyAuthentication;
import com.iconsoft.afroeats.GestionNotification.GestionEmail.Templates.FormatEmail;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.*;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AccountMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppUserMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppUser;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.DriverAfro;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.json.JSONObject;
@Service
@Transactional
public class AccountService implements AccountMetier {

    @Autowired
    AppUserMetier appUserMetier;
    @Autowired
    ServiceCloudinary serviceCloudinary;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUserDTO registerClient(RegisterDTO registerDTO) {
        AppUser u = appUserMetier.getUserByLogin(registerDTO.getEmail().trim());
        if (u != null)
            throw new ErrorMessages("Cette email est deja utilisée par un autre utilisateur!!!", HttpStatus.CONFLICT);
        u = appUserMetier.getUserByLogin(registerDTO.getMobilenumber());
        if (u != null)
            throw new ErrorMessages("Ce numéro de téléphone est deja utilisé par un autre utilisateur!!!", HttpStatus.CONFLICT);
        registerDTO.setEmail(registerDTO.getEmail().trim());
        if (registerDTO.isClient()) {
            return appUserMetier.saveClient(registerDTO);
        } else if (registerDTO.isFeeder()) {
            return appUserMetier.saveAfromama(registerDTO);
        } else if (registerDTO.isDriver()) {
            return appUserMetier.saveLivreur(registerDTO);
        }
        return null;
    }

    @Override
    public AppUserDTO loginAccount(LoginDTO loginDTO) {
        AppUser user = appUserMetier.getUserByLogin(loginDTO.getEmail());
        if (user == null)
            throw new ErrorMessages("Utilisateur introuvable", HttpStatus.NOT_FOUND);

        if (user.isFeeder()) {
            Afromama afromama = (Afromama) user;
            if (!afromama.isConfirmregister())
                throw new ErrorMessages("Chez Afromama votre compte n a pas encore été activé bien vouloir contacter l'admin", HttpStatus.NOT_FOUND);
        }

        return AppUserService.permuteAppUserToAppUserDTO(user);
    }

    @Override
    public AppUserDTO registerDriver(RegisterDTO registerDTO) {
        AppUser u = appUserMetier.getUserByLogin(registerDTO.getEmail());
        if (u != null)
            throw new ErrorMessages("Cette email est deja utilisée par un autre utilisateur!!!", HttpStatus.CONFLICT);
        u = appUserMetier.getUserByLogin(registerDTO.getMobilenumber());
        if (u != null)
            throw new ErrorMessages("Ce numéro de téléphone est deja utilisé par un autre utilisateur!!!", HttpStatus.CONFLICT);

        if (registerDTO.isDriver()) {
            return appUserMetier.saveLivreur(registerDTO);
        } else {
            throw new ErrorMessages("Impossible de poursuivre car vous n'avez pas activer l'option livreur", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public AppUserDTO confirmeidentity(DocumentCompleter documentCompleter) {
        DriverAfro driverAfro = appUserMetier.finddriverByReference(documentCompleter.getReference());
        if (driverAfro == null)
            throw new ErrorMessages("Impossible de poursuivre car vous n est pas dans le systeme", HttpStatus.NOT_FOUND);
        if (documentCompleter.getAdresseimage() != null && !documentCompleter.getAdresseimage().contains("cloudinary.com/icon-soft")) {
            driverAfro.setAdresseimage(serviceCloudinary.saveObjectOnCloudinary(
                    documentCompleter.getAdresseimage(),
                    "users",
                    driverAfro.getReference(),
                    "preuveadresse"
            ));
        }
        if (documentCompleter.getAssurancedriver() != null && !documentCompleter.getAssurancedriver().contains("cloudinary.com/icon-soft")) {
            driverAfro.setAssurancedriver(serviceCloudinary.saveObjectOnCloudinary(
                    documentCompleter.getAssurancedriver(),
                    "users",
                    driverAfro.getReference(),
                    "assurance"
            ));
        }
        if (documentCompleter.getCardriver() != null && !documentCompleter.getCardriver().contains("cloudinary.com/icon-soft")) {
            driverAfro.setCardriver(serviceCloudinary.saveObjectOnCloudinary(
                    documentCompleter.getCardriver(),
                    "users",
                    driverAfro.getReference(),
                    "vehicule"
            ));
        }
        if (documentCompleter.getIdentitydriver1() != null && !documentCompleter.getIdentitydriver1().contains("cloudinary.com/icon-soft")) {
            driverAfro.setIdentitydriver1(serviceCloudinary.saveObjectOnCloudinary(
                    documentCompleter.getIdentitydriver1(),
                    "users",
                    driverAfro.getReference(),
                    "cnirecto"
            ));
        }
        if (documentCompleter.getIdentitydriver2() != null && !documentCompleter.getIdentitydriver2().contains("cloudinary.com/icon-soft")) {
            driverAfro.setIdentitydriver2(serviceCloudinary.saveObjectOnCloudinary(
                    documentCompleter.getIdentitydriver2(),
                    "users",
                    driverAfro.getReference(),
                    "cniverso"
            ));
        }
        if (documentCompleter.getLicencedriver() != null && !documentCompleter.getLicencedriver().contains("cloudinary.com/icon-soft")) {
            driverAfro.setLicencedriver(serviceCloudinary.saveObjectOnCloudinary(
                    documentCompleter.getLicencedriver(),
                    "users",
                    driverAfro.getReference(),
                    "licence"
            ));
        }
        if (driverAfro.getIdentitydriver1() == null || driverAfro.getIdentitydriver2() == null ||
                driverAfro.getAdresseimage() == null || driverAfro.getAssurancedriver() == null ||
                driverAfro.getLicencedriver() == null || driverAfro.getCardriver() == null) {
            driverAfro.setEtatevolutiondoc(EtatEvolutionDoccument.NON_VERIFIER);
        } else {
            driverAfro.setEtatevolutiondoc(EtatEvolutionDoccument.ENCOURS_VERIFICATION);
        }
        driverAfro.setModelivraison(documentCompleter.getModelivraison());
        return AppUserService.permuteAppUserToAppUserDTO(driverAfro);
    }

    @Override
    public String changePassword(ChangePassword changePassword) {
        String message;
        Gson g = new Gson();
        if (changePassword.getOldpassword().equals(changePassword.getNewpassword())) {
            throw new ErrorMessages("Votre nouveau mot de passe doit être différent de l'ancien.",HttpStatus.CONFLICT);
        }
        AppUser user= appUserMetier.findUserByReference(changePassword.getReferenceuser());
        if(user==null) throw new ErrorMessages("L'utilisateur entrer n'existe pas.", HttpStatus.NOT_FOUND);

        if (!bCryptPasswordEncoder.matches(changePassword.getOldpassword(), user.getPassword())) {
            throw new ErrorMessages( "Votre ancien mot de passe est incorrect.",HttpStatus.BAD_REQUEST);

        }
        if(!changePassword.getNewpassword().equals("")){
            if (changePassword.getNewpassword().length()<8) {
                throw new ErrorMessages("Le mot de passe doit contenir au moins 8 caracters .",HttpStatus.CONFLICT);
            }
            String encryptedPassword = bCryptPasswordEncoder.encode(changePassword.getNewpassword());
            user.setPassword(encryptedPassword);
            message="Le mode passe a été mise à jour avec succes";

            return g.toJson(message);
        } else {
            message="Un probleme non attend est survenu lors de la mise à jour";
            return g.toJson(message);
        }
    }

}
