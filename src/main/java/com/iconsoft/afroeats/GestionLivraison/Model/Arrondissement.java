package com.iconsoft.afroeats.GestionLivraison.Model;

public class Arrondissement {
    private Long id;
    private String nomarondissement;
    private int codepostal;

    public Arrondissement() {
    }

    public Arrondissement(String nomarondissement, int codepostal) {
        this.nomarondissement = nomarondissement;
        this.codepostal = codepostal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomarondissement() {
        return nomarondissement;
    }

    public void setNomarondissement(String nomarondissement) {
        this.nomarondissement = nomarondissement;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }
}
