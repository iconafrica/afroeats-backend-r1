package com.iconsoft.afroeats.GestionProduits.WebRestController;


import com.iconsoft.afroeats.GestionProduits.DTO.MenusDTO;
import com.iconsoft.afroeats.GestionProduits.DTO.PlatJourDto;
import com.iconsoft.afroeats.GestionProduits.Metier.MenusMetier;
import com.iconsoft.afroeats.GestionProduits.Models.Menus;
import com.iconsoft.afroeats.GestionProduits.Models.Plat;
import com.iconsoft.afroeats.GestionRestaurant.Model.PointDeVente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("menus")
public class MenusRestController {

    @Autowired
    MenusMetier menusMetier;

    @PostMapping("create")
    Menus createMenus(@RequestBody MenusDTO menusDTO){
        return menusMetier.createMenus(menusDTO);
    }

    @PostMapping("update")
    Menus updateMenus(@RequestBody MenusDTO menusDTO){
        return menusMetier.updatePlats(menusDTO);
    }

    @GetMapping("getmenusdujouretlendemain")
    MenusDTO getMenusDuJourEtLendemain(){
        return menusMetier.getMenusDuJourEtLendemain();
    }

    @GetMapping("getmenusbypage")
    Page<Menus> getMenusByPage(Pageable pageable){
        return menusMetier.getAllMenusByPage(pageable);
    }

    @GetMapping("getmenusbylist")
    List<Menus> getMenusByList(){
        return menusMetier.getAllMenusByList();
    }

    @GetMapping("getmenusbyrefrestaurant/{referencerestaurant}")
    List<Menus> getMenusByAfromama(@PathVariable String referencerestaurant){
        return menusMetier.getMenusByReferenceRestaurant(referencerestaurant);
    }

    @DeleteMapping("delete/{referencemenu}/{referencearticle}")
    Menus deleteArticleToMenu(@PathVariable String referencemenu,@PathVariable String referencearticle){
        return menusMetier.deleteArticleToMenu(referencemenu,referencearticle);
    }

    @GetMapping("getAllplatjour")
    List<PlatJourDto> getAllPlatJour(){
        return menusMetier.getAllPlatJour();
    }
}
