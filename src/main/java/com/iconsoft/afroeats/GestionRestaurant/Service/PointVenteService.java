package com.iconsoft.afroeats.GestionRestaurant.Service;

import com.iconsoft.afroeats.Cloudinary.Services.ServiceCloudinary;
import com.iconsoft.afroeats.Configuration.GenerateRandom;
import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionProduits.DTO.MenusDTO;
import com.iconsoft.afroeats.GestionProduits.Metier.MenusMetier;
import com.iconsoft.afroeats.GestionProduits.Models.Menus;
import com.iconsoft.afroeats.GestionRestaurant.Dao.PointDeVenteDao;
import com.iconsoft.afroeats.GestionRestaurant.Dao.RestaurantDao;
import com.iconsoft.afroeats.GestionRestaurant.Dto.PointVenteSaveDTO;
import com.iconsoft.afroeats.GestionRestaurant.Metier.PointVenteMetier;
import com.iconsoft.afroeats.GestionRestaurant.Model.PointDeVente;
import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import com.iconsoft.afroeats.GestionUtilisateurs.Models.Afromama;
import com.iconsoft.afroeats.GestionUtilisateurs.Repository.AfroMamaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PointVenteService implements PointVenteMetier {
    @Autowired
    PointDeVenteDao pointDeVenteDao;
    @Autowired
    RestaurantDao restaurantDao;
    @Autowired
    AfroMamaRepository afroMamaRepository;
    @Autowired
    MenusMetier menusMetier;
    @Autowired
    ServiceCloudinary serviceCloudinary;


    @Override
    public PointDeVente findPointDeVenteByReference(String reference) {
        return pointDeVenteDao.findPointDeVenteByReference(reference);
    }

    @Override
    public Restaurant findRestaurantByAndStatusIsTrue(String reference) {
        return restaurantDao.findRestaurantByReferenceAndStatusIsTrue(reference);
    }

    @Override
    public Restaurant saveRestaurant(PointVenteSaveDTO pointVenteSaveDTO) {

        if (pointDeVenteDao.findPointDeVenteByLibelle(pointVenteSaveDTO.getLibelle()) != null)
            throw new ErrorMessages("Il faut changer le nom du restaurant car il existe un restaurant de ce nom", HttpStatus.CONFLICT);
        Afromama afromama = afroMamaRepository.findByReference(pointVenteSaveDTO.getReferenceafro());
        if (afromama == null)
            throw new ErrorMessages("L'afromama choisi n'existe pas ", HttpStatus.CONFLICT);
        if (afromama.getRestaurant() != null)
            throw new ErrorMessages("Le point de vente " + afromama.getRestaurant().getLibelle() + " est deja associé a cet afromama", HttpStatus.CONFLICT);
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(pointVenteSaveDTO, restaurant);
        restaurant.setAfromama(afromama);
        restaurant.setReference(GenerateRandom.randomString(8));
        afromama.setConfirmregister(true);
        restaurant = restaurantDao.save(restaurant);
        restaurant.setImage(serviceCloudinary.saveObjectOnCloudinary(pointVenteSaveDTO.getImage(), "restaurant",
                restaurant.getLibelle().trim(), restaurant.getReference()  ));
        afromama.setRestaurant(restaurant);

        MenusDTO menusDTO = new MenusDTO();
        menusDTO.setReferencerestaurant(restaurant.getReference());
        menusDTO.setLibellerestaurant(restaurant.getLibelle());
        List<Menus> menus = menusMetier.saveLocalMenus(menusDTO);
        for (Menus menu : menus) {
            menu.setRestaurant(restaurant);
        }
        if (menus.size() != 7) {
            throw new ErrorMessages("Une erreur est survenue lors de la creation des menus.", HttpStatus.CONFLICT);
        }
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(PointVenteSaveDTO pointVenteSaveDTO, String referencerestaurant) {
        Restaurant restaurant = restaurantDao.findRestaurantByReference(referencerestaurant);
        if (pointVenteSaveDTO.getLibelle() != null) {
            restaurant.setLibelle(pointVenteSaveDTO.getLibelle());
        }
        if (pointVenteSaveDTO.getDescription() != null) {
            restaurant.setDescription(pointVenteSaveDTO.getDescription());
        }
        if (pointVenteSaveDTO.getImage() != null) {
            if (!pointVenteSaveDTO.getImage().contains("cloudinary.com/icon-soft"))
            restaurant.setImage(serviceCloudinary.saveObjectOnCloudinary(pointVenteSaveDTO.getImage(), "restaurant",
                    restaurant.getLibelle().trim(),restaurant.getReference()  ));
        }
        if (pointVenteSaveDTO.getLatitude() != 0) {
            restaurant.setLatitude(pointVenteSaveDTO.getLatitude());
        }
        if (pointVenteSaveDTO.getLongitude() != 0) {
            restaurant.setLongitude(pointVenteSaveDTO.getLongitude());
        }
        return restaurant;
    }

    @Override
    public PointDeVente findPointDeVenteByLibelle(String libelele) {
        return pointDeVenteDao.findPointDeVenteByLibelle(libelele);
    }

    @Override
    public List<PointDeVente> findAllPointDeVente() {
        return pointDeVenteDao.findAll();
    }

    @Override
    public List<Restaurant> findAllPointDeVente_with_menu() {
        return menusMetier.findAllMenuWithPlat();
    }
}
