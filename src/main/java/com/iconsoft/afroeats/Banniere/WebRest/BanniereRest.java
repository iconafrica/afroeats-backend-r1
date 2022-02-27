package com.iconsoft.afroeats.Banniere.WebRest;

import com.iconsoft.afroeats.Banniere.Metier.MetierBanniere;
import com.iconsoft.afroeats.Banniere.Model.Banniere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("banniere")
public class BanniereRest {
    @Autowired
    MetierBanniere metierBanniere;

    @GetMapping("findbyid/{id}")
    Banniere findByIdbanniere(@PathVariable Long id){
        return metierBanniere.findByIdbanniere(id);
    }
    @DeleteMapping("delectbyid/{id}")
    void delectByIdbanniere(@PathVariable Long id){
        metierBanniere.delectByIdbanniere(id);
    }
    @GetMapping("/findall")
    List<Banniere> findAllBanier(){
        return metierBanniere.findAllBanier();
    }
    @PostMapping("save")
    Banniere savebanniere(@RequestBody Banniere banniere){
        return metierBanniere.savebanniere(banniere);
    }

}
