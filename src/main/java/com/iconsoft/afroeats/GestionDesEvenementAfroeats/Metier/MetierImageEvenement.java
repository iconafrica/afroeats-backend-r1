package com.iconsoft.afroeats.GestionDesEvenementAfroeats.Metier;

import com.iconsoft.afroeats.GestionDesEvenementAfroeats.Model.ImageEvenement;

import java.util.List;

public interface MetierImageEvenement {
    ImageEvenement save(ImageEvenement imageEvenement);
    void  delete(Long id);
    List<ImageEvenement> findAllImageEvenement();
}
