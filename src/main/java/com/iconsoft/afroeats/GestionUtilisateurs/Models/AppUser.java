package com.iconsoft.afroeats.GestionUtilisateurs.Models;

import com.iconsoft.afroeats.GestionCommande.Models.Adresse;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "utilisateur")
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser implements Serializable {
    @Id
    @GeneratedValue
    private Long iduser;
    private String status; //inactif, actif, supprimé
    private Date deletedat; // Date de suppression
    @NotNull
    private String fullname;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String mobilenumber;
    private boolean mobilenumberverified;

    @Column(columnDefinition = "text")
    private String photoprofile;
    private String facebooktoken;
    private String googletoken;
    private String stripecustomerid;
    private Date createdat;
    private Date updateat;
    private boolean client;
    private boolean driver;
    private boolean feeder;
    private boolean admin;
    private String sexe;
    @NotNull
    private String reference;
    @Column(columnDefinition = "boolean")
    private boolean validate = false;

    @ManyToMany(fetch = FetchType.EAGER)//EAGER veut dire qu'a chaque fois que je charge un utilisateur il charge aussi ses roles
    //FetchType.EAGER : indique que la relation doit être chargée en même temps que l'entité qui la porte.
    Set<AppRole> roles =new HashSet<>();

    @OneToOne(mappedBy = "appuser")
    Adresse adresses;

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



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public void setMobilenumber(String mobileNumber) {
        this.mobilenumber = mobileNumber;
    }


    public boolean isMobilenumberverified() {
        return mobilenumberverified;
    }

    public void setMobilenumberverified(boolean mobileNumberVerified) {
        this.mobilenumberverified = mobileNumberVerified;
    }



    public String getPhotoprofile() {
        return photoprofile;
    }

    public void setPhotoprofile(String image) {
        this.photoprofile = image;
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

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
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

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDeletedat() {
        return deletedat;
    }

    public void setDeletedat(Date deletedat) {
        this.deletedat = deletedat;
    }

    public Adresse getAdresses() {
        return adresses;
    }

    public void setAdresses(Adresse adresses) {
        this.adresses = adresses;
    }
}
