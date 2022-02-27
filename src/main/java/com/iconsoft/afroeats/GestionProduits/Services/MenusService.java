package com.iconsoft.afroeats.GestionProduits.Services;

import com.iconsoft.afroeats.Configuration.GenerateRandom;
import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionProduits.DTO.MenusDTO;
import com.iconsoft.afroeats.GestionProduits.DTO.PlatJourDto;
import com.iconsoft.afroeats.GestionProduits.Metier.MenusMetier;
import com.iconsoft.afroeats.GestionProduits.Models.Menus;
import com.iconsoft.afroeats.GestionProduits.Models.Plat;
import com.iconsoft.afroeats.GestionProduits.Repository.MenusRepository;
import com.iconsoft.afroeats.GestionProduits.Repository.PlatRepository;
import com.iconsoft.afroeats.GestionRestaurant.Dao.PointDeVenteDao;
import com.iconsoft.afroeats.GestionRestaurant.Dao.RestaurantDao;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import com.iconsoft.afroeats.GestionUtilisateurs.Repository.AfroMamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MenusService implements MenusMetier {
    @Autowired
    MenusRepository menusRepository;
    @Autowired
    PlatRepository platRepository;
    @Autowired
    AfroMamaRepository afroMamaRepository;
    @Autowired
    RestaurantDao restaurantDao;

    @Override
    public Menus createMenus(MenusDTO menusDTO) {
        if(menusDTO.getPlats().isEmpty()) throw new ErrorMessages("Vous ne pouvez poursuivre car ce menus ne contient aucun plat.", HttpStatus.NOT_FOUND);
        Restaurant restaurant = restaurantDao.findRestaurantByReference(menusDTO.getReferencerestaurant());
        if(restaurant==null) throw new ErrorMessages("Le Restaurant n'existe pas.", HttpStatus.NOT_FOUND);
        /*menusDTO.getPlats().forEach(p->{
            if(!p.getRestaurant().getAfromama().getIduser().equals(restaurant..getIduser())){
                throw new ErrorMessages("Impossible de poursuivre. Les plats doivent appartenir à un même utilisateur.", HttpStatus.CONFLICT);
            }
        });
*/
        Menus menus = new Menus();
        menus.setCreatedat(new Date());
        menus.setPlats(menusDTO.getPlats());
        menus.setReference(GenerateRandom.randomString(6));
        menus.setReferencerestaurant(restaurant.getReference());
        if(controlJour(menusDTO.getJour())){
            menus.setJour(menusDTO.getJour());
            menus.setNumero(getJourOrder(menusDTO.getJour()));
        } else {
            throw new ErrorMessages("Vérifier le jour de programmation de ce menus.", HttpStatus.BAD_REQUEST);
        }

        return menusRepository.save(menus);
    }

    @Override
    public List<Menus> saveLocalMenus(MenusDTO menusDTO) {
        List<Menus> menusList = new ArrayList<>();

        menusList.add(saveByJour(menusDTO, "MONDAY"));
        menusList.add(saveByJour(menusDTO, "TUESDAY"));
        menusList.add(saveByJour(menusDTO, "WEDNESDAY"));
        menusList.add(saveByJour(menusDTO, "THURSDAY"));
        menusList.add(saveByJour(menusDTO, "FRIDAY"));
        menusList.add(saveByJour(menusDTO, "SATURDAY"));
        menusList.add(saveByJour(menusDTO, "SUNDAY"));

        return menusRepository.saveAll(menusList);
    }

    Menus saveByJour(MenusDTO menusDTO,String jour){
        Menus menus = new Menus();
        menus.setCreatedat(new Date());
        menus.setPlats(new ArrayList<>());
        menus.setReference(GenerateRandom.randomString(6));
        menus.setReferencerestaurant(menusDTO.getReferencerestaurant());

        if(controlJour(jour)){
            menus.setJour(jour);
            menus.setNumero(getJourOrder(jour));
        } else {
            throw new ErrorMessages("Vérifier le jour de programmation de ce menus.", HttpStatus.BAD_REQUEST);
        }
        return menusRepository.save(menus);
    }

    @Override
    public MenusDTO getMenusDuJourEtLendemain() {
        LocalDate currentDate = LocalDate.now();
        String day = currentDate.getDayOfWeek().name();

        MenusDTO menusDTO = new MenusDTO();
        /*menusDTO.setMenusdujour(menusRepository.findAllByJour(day));
        menusDTO.setMenusdulendemain(menusRepository.findAllByJour(getJourSuivant(day)));*/
        return menusDTO;
    }

    @Override
    public Page<Menus> getAllMenusByPage(Pageable pageable) {
        return menusRepository.findAllByOrderByNumeroAsc(pageable);
    }

    @Override
    public List<Menus> getAllMenusByList() {
        return menusRepository.getAllByOrderByNumeroAsc();
    }

    @Override
    public List<Menus> getMenusByReferenceRestaurant(String reference) {
        return menusRepository.findByReferencerestaurant(reference);
    }

    @Override
    public Menus updatePlats(MenusDTO menusDTO) {
        if(menusDTO.getPlats().isEmpty()) throw new ErrorMessages("Vous ne pouvez poursuivre car ce menus ne contient aucun plat.", HttpStatus.NOT_FOUND);
        Restaurant restaurant = restaurantDao.findRestaurantByReference(menusDTO.getReferencerestaurant());
        if(restaurant==null) throw new ErrorMessages("Le restaurant n'existe pas.", HttpStatus.NOT_FOUND);
       /* menusDTO.getPlats().forEach(p->{ //ceci n a pas de sens car est ce que je t'envoie cette variable

            if(!p.getAfromama().getIduser().equals(afromama.getIduser())){
                throw new ErrorMessages("Impossible de poursuivre. Les plats doivent appartenir à un même utilisateur.", HttpStatus.CONFLICT);
            }
        });*/
        Menus menus = menusRepository.findByReference(menusDTO.getReference());
        if(menus==null) throw new ErrorMessages("Le menus que vous souhaité mettre à jour n'existe pas.", HttpStatus.NOT_FOUND);
        //menus.setPlats(menusDTO.getPlats());
        for (Plat plat : menusDTO.getPlats()) {
            Plat plat1=platRepository.findByIdarticle(plat.getIdarticle());
            if (menus.getPlats()!=null && menus.getPlats().size()>0){
                if (menus.getPlats().contains(plat1)){

                }else {
                    menus.getPlats().add(plat1);
                }
            }else {
                menus.getPlats().add(plat1);
            }

        }
        return menus;
    }

    @Override
    public Menus deleteArticleToMenu(String referencemenu, String referencearticle) {
        Menus menus=menusRepository.findByReference(referencemenu);
        if (menus==null)throw new ErrorMessages("Menu non retrouvé",HttpStatus.CONFLICT);
        Plat plat=platRepository.findByReference(referencearticle);
        if (plat==null)throw new ErrorMessages("Plat non retrouvé",HttpStatus.CONFLICT);
        menus.getPlats().remove(plat);
        return menus;
    }

    @Override
    public List<PlatJourDto> getAllPlatJour() {
        List<PlatJourDto> platJourDtos=new ArrayList<>();
        List<Menus> menus=menusRepository.findAll();
        if (menus!=null){
            for (Menus menu : menus) {


                for (Plat plat : menu.getPlats()) {
                    PlatJourDto platJourDto=new PlatJourDto();
                    if (plat!=null){
                        platJourDto.setPlat(plat);
                        platJourDto.setJours(menu.getJour());
                        platJourDtos.add(platJourDto);
                    }

                }

            }
        }
        return platJourDtos;
    }

    @Override
    public List<Restaurant> findAllMenuWithPlat() {
        return menusRepository.findAllMenuWithPlat();
    }

    boolean controlJour(String jour){
        switch (jour){
            case "MONDAY" :
            case "TUESDAY" :
            case "WEDNESDAY" :
            case "THURSDAY" :
            case "FRIDAY" :
            case "SATURDAY" :
            case "SUNDAY" :
                return true;
            default: return false;
        }
    }

    String getJourSuivant(String jour){
        switch (jour){
            case "MONDAY" : return "TUESDAY";
            case "TUESDAY" : return "WEDNESDAY";
            case "WEDNESDAY" : return "THURSDAY";
            case "THURSDAY" : return "FRIDAY";
            case "FRIDAY" : return "SATURDAY";
            case "SATURDAY" : return "SUNDAY";
            case "SUNDAY" : return "MONDAY";
            default: return null;
        }
    }

    int getJourOrder(String jour){
        switch (jour){
            case "MONDAY" : return 1;
            case "TUESDAY" : return 2;
            case "WEDNESDAY" : return 3;
            case "THURSDAY" : return 4;
            case "FRIDAY" : return 5;
            case "SATURDAY" : return 6;
            case "SUNDAY" : return 7;
            default: return 0;
        }
    }
}
