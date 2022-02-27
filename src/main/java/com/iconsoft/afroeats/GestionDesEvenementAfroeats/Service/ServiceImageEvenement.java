package com.iconsoft.afroeats.GestionDesEvenementAfroeats.Service;

import com.iconsoft.afroeats.GestionDesEvenementAfroeats.Dao.DaoImageEvenement;
import com.iconsoft.afroeats.GestionDesEvenementAfroeats.Metier.MetierImageEvenement;
import com.iconsoft.afroeats.GestionDesEvenementAfroeats.Model.ImageEvenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceImageEvenement implements MetierImageEvenement {
    @Autowired
    DaoImageEvenement daoImageEvenement;

    @Override
    public ImageEvenement save(ImageEvenement imageEvenement) {
        return daoImageEvenement.save(imageEvenement);
    }

    @Override
    public void delete(Long id) {
        daoImageEvenement.deleteById(id);

    }

    @Override
    public List<ImageEvenement> findAllImageEvenement() {
        return daoImageEvenement.findAll();
    }
}
