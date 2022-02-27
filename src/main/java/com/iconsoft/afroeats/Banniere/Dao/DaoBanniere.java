package com.iconsoft.afroeats.Banniere.Dao;

import com.iconsoft.afroeats.Banniere.Model.Banniere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoBanniere extends JpaRepository<Banniere,Long> {
    Banniere findByIdbanniere(Long id);
}
