package com.iconsoft.afroeats.GestionProduits.Models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "produit")
@PrimaryKeyJoinColumn(name = "idarticle")
public class Produit extends Article{

}
