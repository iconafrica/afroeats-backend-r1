package com.iconsoft.afroeats.GestionUtilisateurs.DTO;

import com.iconsoft.afroeats.GestionCommande.Models.Adresse;
import com.iconsoft.afroeats.GestionPanier.Models.Panier;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.AppRole;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppUserDTO {

    private Long iduser;
    private String status; //holde, inactive, active, delete
    private Date deletedat; // Date de suppression
    private String companyname;
    private String fullname;
    private String lastname;
    private String streetnumber;
    private String street;
    private int codepostal;
    private String email;
    private String password;
    private boolean confirmregister;
    private String mobilenumber;
    private String countrycode;
    private boolean mobilenumberverified;
    private String adresse;
    private String addlatitude;
    private String addlongitude;
    private String photoprofile;
    private String token;
    private String devicetoken;
    private String facebooktoken;
    private String googletoken;
    private String stripecustomerid;
    private Date createdat;
    private Date updateat;
    private boolean client;
    private boolean driver;
    private boolean feeder;
    private boolean admin;
    private String reference;
    private String cni1;
    private String cn2;
    private String sexe;
    private Panier panier;
    private String cityaddress;
    Restaurant restaurant;
    Set<AppRole> roles =new HashSet<>();
    private String typeafromama; //afromama_professionnelle     afromama_restaurateur   afromama_passionee
    private EtatEvolutionDoccument etatevolutiondoc;//etat d'evolution des doccuments fournis

    private String identitydriver1;
    private String identitydriver2;
    private String adresseimage;
    private String licencedriver;
    private String assurancedriver;
    private String cardriver;
    private String modelivraison;   //A PIED   EN MOTO   EN VOITURE

    private boolean disponible;

    List<Adresse> adresses;

    public AppUserDTO() {
    }

    public AppUserDTO(String password, String email, String phone) {
        this.email = email;
        this.mobilenumber = phone;
        this.password = password;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeletedat() {
        return deletedat;
    }

    public void setDeletedat(Date deletedat) {
        this.deletedat = deletedat;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public boolean isMobilenumberverified() {
        return mobilenumberverified;
    }

    public void setMobilenumberverified(boolean mobilenumberverified) {
        this.mobilenumberverified = mobilenumberverified;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAddlatitude() {
        return addlatitude;
    }

    public void setAddlatitude(String addlatitude) {
        this.addlatitude = addlatitude;
    }

    public String getAddlongitude() {
        return addlongitude;
    }

    public void setAddlongitude(String addlongitude) {
        this.addlongitude = addlongitude;
    }

    public String getPhotoprofile() {
        return photoprofile;
    }

    public void setPhotoprofile(String photoprofile) {
        this.photoprofile = photoprofile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getFacebooktoken() {
        return facebooktoken;
    }

    public void setFacebooktoken(String facebooktoken) {
        this.facebooktoken = facebooktoken;
    }

    public String getGoogletoken() {
        return googletoken;
    }

    public void setGoogletoken(String googletoken) {
        this.googletoken = googletoken;
    }

    public String getStripecustomerid() {
        return stripecustomerid;
    }

    public void setStripecustomerid(String stripecustomerid) {
        this.stripecustomerid = stripecustomerid;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    public boolean isClient() {
        return client;
    }

    public void setClient(boolean client) {
        this.client = client;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public boolean isFeeder() {
        return feeder;
    }

    public void setFeeder(boolean feeder) {
        this.feeder = feeder;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public String getCni1() {
        return cni1;
    }

    public void setCni1(String cni1) {
        this.cni1 = cni1;
    }

    public String getCn2() {
        return cn2;
    }

    public void setCn2(String cn2) {
        this.cn2 = cn2;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTypeafromama() {
        return typeafromama;
    }

    public void setTypeafromama(String typeafromama) {
        this.typeafromama = typeafromama;
    }

    public boolean isConfirmregister() {
        return confirmregister;
    }

    public void setConfirmregister(boolean confirmregister) {
        this.confirmregister = confirmregister;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getIdentitydriver1() {
        return identitydriver1;
    }

    public void setIdentitydriver1(String identitydriver1) {
        this.identitydriver1 = identitydriver1;
    }

    public String getIdentitydriver2() {
        return identitydriver2;
    }

    public void setIdentitydriver2(String identitydriver2) {
        this.identitydriver2 = identitydriver2;
    }

    public String getAdresseimage() {
        return adresseimage;
    }

    public void setAdresseimage(String adresseimage) {
        this.adresseimage = adresseimage;
    }

    public String getLicencedriver() {
        return licencedriver;
    }

    public void setLicencedriver(String licencedriver) {
        this.licencedriver = licencedriver;
    }

    public String getAssurancedriver() {
        return assurancedriver;
    }

    public void setAssurancedriver(String assurancedriver) {
        this.assurancedriver = assurancedriver;
    }

    public String getCardriver() {
        return cardriver;
    }

    public void setCardriver(String cardriver) {
        this.cardriver = cardriver;
    }

    public String getModelivraison() {
        return modelivraison;
    }

    public void setModelivraison(String modelivraison) {
        this.modelivraison = modelivraison;
    }

    public List<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public EtatEvolutionDoccument getEtatevolutiondoc() {
        return etatevolutiondoc;
    }

    public void setEtatevolutiondoc(EtatEvolutionDoccument etatevolutiondoc) {
        this.etatevolutiondoc = etatevolutiondoc;
    }

    public String getCityaddress() {
        return cityaddress;
    }

    public void setCityaddress(String cityaddress) {
        this.cityaddress = cityaddress;
    }
}
