package com.iconsoft.afroeats.Banniere.Service;

import com.iconsoft.afroeats.Banniere.Dao.DaoBanniere;
import com.iconsoft.afroeats.Banniere.Metier.MetierBanniere;
import com.iconsoft.afroeats.Banniere.Model.Banniere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceBanniere implements MetierBanniere {
    @Autowired
    DaoBanniere daoBanniere;
    @Override
    public Banniere findByIdbanniere(Long id) {
        return daoBanniere.findByIdbanniere(id);
    }

    @Override
    public void delectByIdbanniere(Long id) {
        daoBanniere.deleteById(id);
    }

    @Override
    public List<Banniere> findAllBanier() {
        return daoBanniere.findAll();
    }

    @Override
    public Banniere savebanniere(Banniere banniere) {
        return daoBanniere.save(new Banniere(banniere.getDescription(),banniere.getImage()));
    }
}
