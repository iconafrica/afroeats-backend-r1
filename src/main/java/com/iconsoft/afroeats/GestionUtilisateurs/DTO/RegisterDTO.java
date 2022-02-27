package com.iconsoft.afroeats.GestionUtilisateurs.DTO;

import com.iconsoft.afroeats.GestionCommande.Models.Adresse;

import java.util.List;

public class RegisterDTO {
    private String fullname;
    private String streetnumber;
    private String street;
    private int codepostal;
    private String email;
    private String photoprofile;
    private String password;
    private String mobilenumber;
    private String countrycode;
    private String devicetoken;
    private String facebooktoken;
    private String cityaddress;
    private String googletoken;
    private String sexe;
    private boolean client;
    private boolean driver;
    private boolean feeder;
    private boolean admin;

    //Informations pour le driver
    private String identitydriver1;
    private String identitydriver2;
    private String adresseimage;
    private String licencedriver;
    private String assurancedriver;
    private String cardriver;
    private String modelivraison;   //A PIED   EN MOTO   EN VOITURE

    private List<Adresse> adresses;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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

    public String getPhotoprofile() {
        return photoprofile;
    }

    public void setPhotoprofile(String photoprofile) {
        this.photoprofile = photoprofile;
    }

    public String getCityaddress() {
        return cityaddress;
    }

    public void setCityaddress(String cityaddress) {
        this.cityaddress = cityaddress;
    }
}
