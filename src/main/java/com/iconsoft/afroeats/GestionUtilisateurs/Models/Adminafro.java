package com.iconsoft.afroeats.GestionUtilisateurs.Models;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "iduser")
public class Adminafro extends AppUser{

}
