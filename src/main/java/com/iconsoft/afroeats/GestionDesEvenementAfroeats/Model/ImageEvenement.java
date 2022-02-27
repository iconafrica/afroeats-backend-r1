package com.iconsoft.afroeats.GestionDesEvenementAfroeats.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ImageEvenement {
    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "text")
    private String imageevenement;
    private Date date;
    private String commentaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageevenement() {
        return imageevenement;
    }

    public void setImageevenement(String imageevenement) {
        this.imageevenement = imageevenement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
