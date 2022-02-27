package com.iconsoft.afroeats.Banniere.Model;

import javax.persistence.*;

@Entity
public class Banniere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idbanniere;
    private String description;
    @Column(columnDefinition = "text")
    private String image;

    public Banniere() {}

    public Banniere(String description, String image) {
        this.description = description;
        this.image = image;
    }

    public Long getIdbanniere() {
        return idbanniere;
    }

    public void setIdbanniere(Long id) {
        this.idbanniere = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
