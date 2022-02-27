package com.iconsoft.afroeats.GestionPanier.WebRestController;

import com.iconsoft.afroeats.GestionPanier.DTO.PanieritemDTO;
import com.iconsoft.afroeats.GestionPanier.Metier.PanierMetier;
import com.iconsoft.afroeats.GestionPanier.Models.Panier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("panier")
public class PanierRestController {
    @Autowired
    PanierMetier panierMetier;

    @PostMapping("/panieraction")
    public boolean manageactionpaniert(@RequestBody PanieritemDTO panieritemDTO){
        return panierMetier.panierItemAction(panieritemDTO);
    }

    @DeleteMapping("/viderpanier/{reference}")
    public Panier viderPanier(@PathVariable String reference){
        return panierMetier.viderPanier(reference);
    }

    @GetMapping("/{reference}")
    public Panier getcartByReference(@PathVariable String reference){
        return panierMetier.findPanierByReference(reference);
    }

    @GetMapping("/panierbyid/{idpanier}")
    public Panier getcartByIdpanier(@PathVariable Long idpanier){
        return panierMetier.findPanierById(idpanier);
    }
}
