package com.iconsoft.afroeats.GestionUtilisateurs.Services;

import com.iconsoft.afroeats.Cloudinary.Services.ServiceCloudinary;
import com.iconsoft.afroeats.Configuration.GenerateRandom;
import com.iconsoft.afroeats.GestionCommande.Models.Adresse;
import com.iconsoft.afroeats.GestionCommande.Dao.AdresseRepository;
import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionNotification.GestionEmail.Service.MyAuthentication;
import com.iconsoft.afroeats.GestionNotification.GestionEmail.Templates.FormatEmail;
import com.iconsoft.afroeats.GestionPanier.Metier.PanierMetier;
import com.iconsoft.afroeats.GestionPanier.Models.Panier;
import com.iconsoft.afroeats.GestionProduits.Metier.MenusMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.AppUserDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.EtatEvolutionDoccument;
import com.iconsoft.afroeats.GestionUtilisateurs.DTO.RegisterDTO;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppRoleMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Metier.AppUserMetier;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.*;
import com.iconsoft.afroeats.GestionUtilisateurs.Repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AppUserService implements AppUserMetier {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    AppRoleMetier appRoleMetier;
    @Autowired
    ClientAfroRepository clientAfroRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AfroMamaRepository afroMamaRepository;
    @Autowired
    DriverAfroRepository driverAfroRepository;
    @Autowired
    PanierMetier panierMetier;
    final
    MenusMetier menusMetier;
    final
    AdresseRepository adresseRepository;
    @Autowired
    ServiceCloudinary serviceCloudinary;
    @Autowired
    private MyAuthentication myAuthentication;

    public AppUserService(BCryptPasswordEncoder bCryptPasswordEncoder, AppUserRepository appUserRepository, AppRoleMetier appRoleMetier, @Lazy MenusMetier menusMetier, @Lazy AdresseRepository adresseRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.appUserRepository = appUserRepository;
        this.appRoleMetier = appRoleMetier;
        this.menusMetier = menusMetier;
        this.adresseRepository = adresseRepository;
    }

    @Override
    public AppUser getUserByLogin(String login) {
        return appUserRepository.findByEmailOrPhone(login);
    }

    @Override
    public AppUserDTO saveClient(RegisterDTO registerDTO) {
        ClientAfro clientAfro = new ClientAfro();
        BeanUtils.copyProperties(registerDTO, clientAfro);
        clientAfro.setPassword(this.bCryptPasswordEncoder.encode(clientAfro.getPassword()));
        clientAfro.setClient(true);
        clientAfro.setReference(GenerateRandom.randomString(8));
        clientAfro.setStatus(AppUserStatus.Active);
        clientAfro.setCreatedat(new Date());
        clientAfro.setUpdateat(new Date());
        if(clientAfro.getPhotoprofile() != null){
            if(!clientAfro.getPhotoprofile().isEmpty()){
                clientAfro.setPhotoprofile(serviceCloudinary.saveObjectOnCloudinary(
                        clientAfro.getPhotoprofile(),
                        "users",
                        clientAfro.getReference(),
                        clientAfro.getReference()
                ));
            }
        }

        clientAfro = clientAfroRepository.save(clientAfro);
        appRoleMetier.addRoleToUser(clientAfro.getIduser(), "CLIENT");
        Adresse adresse=savaAdresse(registerDTO);
       adresse.setAppuser(clientAfro);
        Panier panier = new Panier();
        panier.setNomclient(clientAfro.getFullname());
        panier.setClient(clientAfro);
        panier.setDatecreation(new Date());
        panier.setReference(GenerateRandom.randomString(5));
        panier.setMontanttotal(0);
        panier.setPanieritems(new ArrayList<>());
        panier = panierMetier.savePanier(panier);

        clientAfro.setPanier(panier);

        //Envoie du mail
        myAuthentication.sendMail(registerDTO.getEmail(), FormatEmail.inscriptionSuccessMail, "Inscription réussie");

        return permuteAppUserToAppUserDTO(clientAfro);
    }

    @Override
    public AppUserDTO saveAfromama(RegisterDTO registerDTO) {
        Afromama afromama = new Afromama();
        BeanUtils.copyProperties(registerDTO, afromama);
        afromama.setPassword(this.bCryptPasswordEncoder.encode(afromama.getPassword()));
        afromama.setFeeder(true);
        afromama.setReference(GenerateRandom.randomString(8));
        afromama.setStatus(AppUserStatus.Active);
        afromama.setCreatedat(new Date());
        afromama.setUpdateat(new Date());

        if(afromama.getPhotoprofile() != null){
            if(!afromama.getPhotoprofile().isEmpty()){
                afromama.setPhotoprofile(serviceCloudinary.saveObjectOnCloudinary(
                        afromama.getPhotoprofile(),
                        "users",
                        afromama.getReference(),
                        afromama.getReference()
                ));
            }
        }
        Adresse adresse=savaAdresse(registerDTO);
        afromama = afroMamaRepository.save(afromama);
        appRoleMetier.addRoleToUser(afromama.getIduser(), "CLIENT");
        appRoleMetier.addRoleToUser(afromama.getIduser(), "FEEDER");
        adresse.setAppuser(afromama);
        myAuthentication.sendMail(afromama.getEmail(), FormatEmail.inscriptionSuccessMail, "Inscription réussie");
        return permuteAppUserToAppUserDTO(afromama);
    }

    @Override
    public AppUserDTO saveLivreur(RegisterDTO registerDTO) {
        DriverAfro driverAfro = new DriverAfro();
        BeanUtils.copyProperties(registerDTO, driverAfro);
        driverAfro.setPassword(this.bCryptPasswordEncoder.encode(driverAfro.getPassword()));
        driverAfro.setDriver(true);
        driverAfro.setReference(GenerateRandom.randomString(8));
        driverAfro.setStatus(AppUserStatus.Active);
        driverAfro.setCreatedat(new Date());
        driverAfro.setUpdateat(new Date());

        if(driverAfro.getPhotoprofile() != null){
            if(!driverAfro.getPhotoprofile().isEmpty()){
                driverAfro.setPhotoprofile(serviceCloudinary.saveObjectOnCloudinary(
                        driverAfro.getPhotoprofile(),
                        "users",
                        driverAfro.getReference(),
                        "profil"
                ));
            }
        }

        //chargement des images
        if(driverAfro.getIdentitydriver1() != null){
            if(!driverAfro.getIdentitydriver1().isEmpty()){
                driverAfro.setIdentitydriver1(serviceCloudinary.saveObjectOnCloudinary(
                        driverAfro.getIdentitydriver1(),
                        "users",
                        driverAfro.getReference(),
                        "cnirecto"
                ));
            }
        }
        if(driverAfro.getAdresseimage() != null){
            if(!driverAfro.getAdresseimage().isEmpty()){
                driverAfro.setAdresseimage(serviceCloudinary.saveObjectOnCloudinary(
                        driverAfro.getAdresseimage(),
                        "users",
                        driverAfro.getReference(),
                        "preuveadresse"
                ));
            }
        }
        if(driverAfro.getLicencedriver() != null){
            if(!driverAfro.getLicencedriver().isEmpty()){
                driverAfro.setLicencedriver(serviceCloudinary.saveObjectOnCloudinary(
                        driverAfro.getLicencedriver(),
                        "users",
                        driverAfro.getReference(),
                        "licence"
                ));
            }
        }
        if(driverAfro.getAssurancedriver() != null){
            if(!driverAfro.getAssurancedriver().isEmpty()){
                driverAfro.setAssurancedriver(serviceCloudinary.saveObjectOnCloudinary(
                        driverAfro.getAssurancedriver(),
                        "users",
                        driverAfro.getReference(),
                        "assurance"
                ));
            }
        }
        if(driverAfro.getCardriver() != null){
            if(!driverAfro.getCardriver().isEmpty()){
                driverAfro.setCardriver(serviceCloudinary.saveObjectOnCloudinary(
                        driverAfro.getCardriver(),
                        "users",
                        driverAfro.getReference(),
                        "vehicule"
                ));
            }
        }
        if(driverAfro.getIdentitydriver2() != null){
            if(!driverAfro.getIdentitydriver2().isEmpty()){
                driverAfro.setIdentitydriver2(serviceCloudinary.saveObjectOnCloudinary(
                        driverAfro.getIdentitydriver2(),
                        "users",
                        driverAfro.getReference(),
                        "cniverso"
                ));
            }
        }
        driverAfro.setModelivraison(registerDTO.getModelivraison());
        driverAfro.setDisponible(false);

        driverAfro = driverAfroRepository.save(driverAfro);
        appRoleMetier.addRoleToUser(driverAfro.getIduser(), "CLIENT");
        appRoleMetier.addRoleToUser(driverAfro.getIduser(), "DRIVER");

        Adresse adresse = savaAdresse(registerDTO);
        adresse.setAppuser(driverAfro);

        if (driverAfro.getIdentitydriver1()==null || driverAfro.getIdentitydriver2()==null ||
                driverAfro.getAdresseimage()==null || driverAfro.getAssurancedriver()==null||
                driverAfro.getLicencedriver()==null||driverAfro.getCardriver()==null){
            driverAfro.setEtatevolutiondoc(EtatEvolutionDoccument.NON_VERIFIER);
        }else{
            driverAfro.setEtatevolutiondoc(EtatEvolutionDoccument.ENCOURS_VERIFICATION);
        }
        myAuthentication.sendMail(driverAfro.getEmail(), FormatEmail.inscriptionSuccessMail, "Inscription réussie");

        return permuteAppUserToAppUserDTO(driverAfro);
    }

    Adresse savaAdresse(RegisterDTO registerDTO){
        Adresse adresse = new Adresse();
        adresse.setCityaddress(registerDTO.getCityaddress());
        adresse.setCodepostal(registerDTO.getCodepostal());
        adresse.setStreetnumber(registerDTO.getStreetnumber());
        adresse.setStreetaddress(registerDTO.getStreet());
        adresse = adresseRepository.save(adresse);
        return adresse;
    }

    @Override
    public AppUserDTO confirm_register(String reference_user) {
        AppUser appUser=appUserRepository.findByReference(reference_user);
        if (appUser.isFeeder()){
            Afromama afromama= (Afromama) appUser;
            afromama.setConfirmregister(!afromama.isConfirmregister());
        }else if (appUser.isDriver()){
            DriverAfro driverAfro= (DriverAfro) appUser;
            driverAfro.setConfirmregister(!driverAfro.isConfirmregister());
        }

        return permuteAppUserToAppUserDTO(appUser);
    }

    @Override
    public AppUser saveAdmin(AppUserDTO appUserDTO) {
        Adminafro adminafro = new Adminafro();
        BeanUtils.copyProperties(appUserDTO, adminafro);
        adminafro.setPassword(this.bCryptPasswordEncoder.encode(adminafro.getPassword()));

        adminafro.setAdmin(true);
        adminafro.setReference(GenerateRandom.randomString(8));
        adminafro.setStatus(AppUserStatus.Active);
        adminafro.setCreatedat(new Date());
        adminafro.setUpdateat(new Date());
        adminafro = adminRepository.save(adminafro);

        appRoleMetier.addRoleToUser(adminafro.getIduser(), "CLIENT");
        appRoleMetier.addRoleToUser(adminafro.getIduser(), "FEEDER");
        appRoleMetier.addRoleToUser(adminafro.getIduser(), "DRIVER");
        appRoleMetier.addRoleToUser(adminafro.getIduser(), "ADMIN");

        return adminafro;
    }

    @Override
    public AppUserDTO findUserByIdUser(Long iduser) {
        return permuteAppUserToAppUserDTO(appUserRepository.findByIduser(iduser));
    }

    @Override
    public Afromama getByname(String name) {
        return afroMamaRepository.findByFullname(name);
    }

    @Override
    public List<AppUserDTO> getAllDrivers() {
        return permuteDriverafroListToAppUserDTOList(driverAfroRepository.findAll());
    }

    @Override
    public List<AppUserDTO> getAllAfromamas() {
        return permuteAfromamaListToAppUserDTOList(afroMamaRepository.findAll());
    }

    @Override
    public List<AppUserDTO> getAllClients() {
        return permuteClientafroListToAppUserDTOList(clientAfroRepository.findAll());
    }

    @Override
    public AppUserDTO findByReferenceAfromama(String reference) {
        return permuteAppUserToAppUserDTO(afroMamaRepository.findByReference(reference));
    }

    @Override
    public AppUserDTO findByreferenceRestaurant(String reference) {
        return permuteAppUserToAppUserDTO(afroMamaRepository.findByreferenceRestaurant(reference));
    }

    @Override
    public boolean setDisponibilite(String reference) {
        AppUser appUser=appUserRepository.findByReference(reference);
        DriverAfro driverAfro = (DriverAfro) appUser;
        driverAfro.setDisponible(!driverAfro.isDisponible());
        return driverAfro.isDisponible();
    }

    @Override
    public AppUserDTO updateProfil(AppUserDTO appUserDTO) {
        AppUser user = appUserRepository.findByReference(appUserDTO.getReference());
        if(user == null) throw new ErrorMessages("L'utilisateur n'existe pas.", HttpStatus.NOT_FOUND);
        if(appUserDTO.getFullname() != null ){
            user.setFullname(appUserDTO.getFullname());
        }
        if(appUserDTO.getMobilenumber() != null){
            user.setMobilenumber(appUserDTO.getMobilenumber());
            user.setMobilenumberverified(false);
        }
        if(appUserDTO.getEmail() != null){
            user.setEmail(appUserDTO.getEmail());
        }
        if(appUserDTO.getPhotoprofile() != null){
            if (!appUserDTO.getPhotoprofile().contains("cloudinary.com/icon-soft")){
                user.setPhotoprofile(serviceCloudinary.saveObjectOnCloudinary(
                        appUserDTO.getPhotoprofile(),
                        "users",
                        user.getReference(),
                        "profil"
                ));
            }

        }
        user.setSexe(appUserDTO.getSexe());
        user.getAdresses().setCodepostal(appUserDTO.getCodepostal());
        user.getAdresses().setStreetnumber(appUserDTO.getStreetnumber());
        user.getAdresses().setStreetaddress(appUserDTO.getStreet());
        user.getAdresses().setCityaddress(appUserDTO.getCityaddress());
        return permuteAppUserToAppUserDTO(user);
    }

    @Override
    public DriverAfro finddriverByReference(String reference) {
        return driverAfroRepository.findByReference(reference);
    }

    @Override
    public AppUser findUserByReference(String reference) {
        return appUserRepository.findByReference(reference);
    }

    @Override
    public AppUserDTO findClientByReference(String referenceClient) {
        return permuteAppUserToAppUserDTO(clientAfroRepository.findByReference(referenceClient));
    }

    @Override
    public String getPhotoClientByName(String name) {
        return appUserRepository.findPhotoByNom(name);
    }

    public static AppUserDTO permuteAppUserToAppUserDTO(AppUser user){
        AppUserDTO appUserDTO = new AppUserDTO();
        BeanUtils.copyProperties(user, appUserDTO);
        if (user.getAdresses()!=null){

            appUserDTO.setCityaddress(user.getAdresses().getCityaddress());
            appUserDTO.setCodepostal(user.getAdresses().getCodepostal());
            appUserDTO.setStreet(user.getAdresses().getStreetaddress());
            appUserDTO.setStreetnumber(user.getAdresses().getStreetnumber());
        }

        return appUserDTO;
    }

    static List<AppUserDTO> permuteDriverafroListToAppUserDTOList(List<DriverAfro> driverAfros){
        List<AppUserDTO> appUserDTOS = new ArrayList<>();
        driverAfros.forEach(r->{
            appUserDTOS.add(permuteAppUserToAppUserDTO(r));
        });
        return appUserDTOS;
    }

    static List<AppUserDTO> permuteAfromamaListToAppUserDTOList(List<Afromama> afromamas){
        List<AppUserDTO> appUserDTOS = new ArrayList<>();
        afromamas.forEach(r->{
            appUserDTOS.add(permuteAppUserToAppUserDTO(r));
        });
        return appUserDTOS;
    }

    static List<AppUserDTO> permuteClientafroListToAppUserDTOList(List<ClientAfro> clientafros){
        List<AppUserDTO> appUserDTOS = new ArrayList<>();
        clientafros.forEach(r->{
            appUserDTOS.add(permuteAppUserToAppUserDTO(r));
        });
        return appUserDTOS;
    }
}
