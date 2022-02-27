package com.iconsoft.afroeats.GestionRestaurant.Dao;

import com.iconsoft.afroeats.GestionRestaurant.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantDao extends JpaRepository<Restaurant,Long> {
    Restaurant findRestaurantByReferenceAndStatusIsTrue(String reference);
    Restaurant findRestaurantByReference(String reference);

}
