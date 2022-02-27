package com.iconsoft.afroeats.GestionDesEvenementAfroeats.Web;

import com.iconsoft.afroeats.GestionDesEvenementAfroeats.Metier.MetierImageEvenement;
import com.iconsoft.afroeats.GestionDesEvenementAfroeats.Model.ImageEvenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("evenements")
public class RestControllerImage {
    @Autowired
    MetierImageEvenement metierImageEvenement;


    @PostMapping("save")
    ImageEvenement save(@RequestBody ImageEvenement imageEvenement){
     return metierImageEvenement.save(imageEvenement);
    }
    @DeleteMapping("delete/{id}")
    void  delete(@PathVariable  Long id){
        metierImageEvenement.delete(id);
    }
    @GetMapping("findAll")
    List<ImageEvenement> findAllImageEvenement(){
        return metierImageEvenement.findAllImageEvenement();
    }
}
