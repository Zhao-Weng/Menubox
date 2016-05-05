package com.example.zweng4.menubox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huckzou on 4/29/16.
 */
//"Cravings","Dominos Pizza","Mia Zas","Kofusion","Sitara"
public final class RestaurantDataProvider {

    public static List<Restaurant> restaurantList= new ArrayList<>();
    public static Map<String, Restaurant> restaurantMap = new HashMap<>();

    static {

        addRestaurant("cravings_front_door",
                "Cravings", "Chinese", "Champaign IL", "$");
        addRestaurant("dominos_pizza_front_door",
                "Dominos Pizza", "Italian", "Champaign IL", "$");
        addRestaurant("mia_zas_front_door",
                "Mia Zas", "Italian", "Champaign IL", "$");
        addRestaurant("kofusion_front_door",
                "Kofusion", "Korean", "Champaign IL", "$$");
        addRestaurant("sitara_front_door",
                "Sitara", "Indian", "Urbana IL", "$");
        addRestaurant("sakanaya_front_door",
                "Sakanaya", "Japan", "Champaign IL", "$$");
        addRestaurant("black_dog_front_door",
                "Black Dog", "American", "Urbana IL", "$$");
        addRestaurant("miga_front_door",
                "Miga", "Asian", "Champaign IL", "$$$");
        addRestaurant("v_picasso_front_door",
                "V.Picasso", "American", "Champaign IL", "$$$");
    }

    public static void addRestaurant(String restaurantId, String name, String cuisine,
                              String address, String price)
    {
        Restaurant item = new Restaurant(restaurantId, name, cuisine, address, price);
        restaurantList.add(item);
    }
}
