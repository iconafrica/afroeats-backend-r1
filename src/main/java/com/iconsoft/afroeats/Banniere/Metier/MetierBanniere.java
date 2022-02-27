package com.iconsoft.afroeats.Banniere.Metier;

import com.iconsoft.afroeats.Banniere.Model.Banniere;

import java.util.List;

public interface MetierBanniere {
    Banniere findByIdbanniere(Long id);
    void delectByIdbanniere(Long id);
    List<Banniere> findAllBanier();
    Banniere savebanniere(Banniere banniere);
}
