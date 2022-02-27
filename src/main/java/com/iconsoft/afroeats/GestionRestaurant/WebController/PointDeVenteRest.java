package com.iconsoft.afroeats.GestionRestaurant.WebController;

import com.iconsoft.afroeats.GestionRestaurant.Dto.PointVenteSaveDTO;
import com.iconsoft.afroeats.GestionRestaurant.Metier.PointVenteMetier;
import com.iconsoft.afroeats.GestionRestaurant.Model.PointDeVente;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("pointdevente")
public class PointDeVenteRest {
    @Autowired
    PointVenteMetier pointVenteMetier;

    @PostMapping("save")
    PointDeVente savePointdevante(@RequestBody PointVenteSaveDTO pointVenteSaveDTO){
        return pointVenteMetier.saveRestaurant(pointVenteSaveDTO);
    }
    @PutMapping("update/{referencerestaurant}")
    PointDeVente updatePointdevante(@RequestBody PointVenteSaveDTO pointVenteSaveDTO,@PathVariable String referencerestaurant){
        return pointVenteMetier.updateRestaurant(pointVenteSaveDTO,referencerestaurant);
    }

    @GetMapping("findall")
    List<PointDeVente> findAllPointDeVente(){
        return pointVenteMetier.findAllPointDeVente();
    }

    @GetMapping("findall_restaurant_with_plat")
    List<Restaurant> findAllrestaurant_with_plat(){
        return pointVenteMetier.findAllPointDeVente_with_menu();
    }
    @GetMapping("findby/{referencerestaurant}")
    PointDeVente findPointDeVenteByReference(@PathVariable String referencerestaurant){
        return pointVenteMetier.findPointDeVenteByReference(referencerestaurant);
    }


}
