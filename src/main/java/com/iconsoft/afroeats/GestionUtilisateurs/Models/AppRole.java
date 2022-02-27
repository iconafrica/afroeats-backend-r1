package com.iconsoft.afroeats.GestionUtilisateurs.Models;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "roles")
public class AppRole implements Serializable {
    @Id
    @GeneratedValue
    private Long idrole;
    private String rolename;
    private final Date date = new Date();

    public AppRole(String rolename) {
        this.rolename = rolename;
    }

    public AppRole() {
    }

    public Long getIdrole() {
        return idrole;
    }

    public void setIdrole(Long idrole) {
        this.idrole = idrole;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Date getDate() {
        return date;
    }
}
