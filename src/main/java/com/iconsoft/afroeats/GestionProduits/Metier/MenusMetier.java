package com.iconsoft.afroeats.GestionProduits.Metier;

import com.iconsoft.afroeats.GestionProduits.DTO.MenusDTO;
import com.iconsoft.afroeats.GestionProduits.DTO.PlatJourDto;
import com.iconsoft.afroeats.GestionProduits.Models.Menus;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenusMetier {
    Menus createMenus(MenusDTO menusDTO);
    List<Menus> saveLocalMenus(MenusDTO menusDTO);
    MenusDTO getMenusDuJourEtLendemain();
    Page<Menus> getAllMenusByPage(Pageable pageable);
    List<Menus> getAllMenusByList();
    List<Menus> getMenusByReferenceRestaurant(String reference);
    Menus updatePlats(MenusDTO menusDTO);
    Menus deleteArticleToMenu(String referencemenu,String referencearticle);
    List<PlatJourDto> getAllPlatJour();
    List<Restaurant> findAllMenuWithPlat();
}
