package com.iconsoft.afroeats.GestionPanier.Metier;

import com.iconsoft.afroeats.GestionPanier.DTO.PanieritemDTO;
import com.iconsoft.afroeats.GestionPanier.Models.Panier;
import com.iconsoft.afroeats.GestionPanier.Models.Panieritem;
import java.util.List;

public interface PanierMetier {
    Panier savePanier(Panier panier);
    Panier findPanierById(Long idpanier);
    Panier findPanierByReference(String reference);
    Panier viderPanier(String reference);
    boolean panierItemAction(PanieritemDTO panieritemDTO);

    Panieritem savePanieritem(Panieritem panieritem);
    List<Panieritem> saveAllPanierItems(List<Panieritem> panieritems);
    boolean deletePanierItem(Panieritem panieritem);
    List<Panieritem> getAllCartItemsByReference(String reference);
}
