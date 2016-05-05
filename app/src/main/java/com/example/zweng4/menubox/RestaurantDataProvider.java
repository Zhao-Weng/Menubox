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
        addRestaurant("dominos_front_door",
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

        addRestaurantMap("cravings_front_door","cravings_front_door",
                "Cravings", "Chinese", "Champaign IL", "$");
        addRestaurantMap("dominos_front_door", "dominos_front_door",
                "Dominos Pizza", "Italian", "Champaign IL", "$");
        addRestaurantMap("mia_zas_front_door", "mia_zas_front_door",
                "Mia Zas", "Italian", "Champaign IL", "$");
        addRestaurantMap("kofusion_front_door", "kofusion_front_door",
                "Kofusion", "Korean", "Champaign IL", "$$");
        addRestaurantMap("sitara_front_door", "sitara_front_door",
                "Sitara", "Indian", "Urbana IL", "$");
        addRestaurantMap("sakanaya_front_door", "sakanaya_front_door",
                "Sakanaya", "Japan", "Champaign IL", "$$");
        addRestaurantMap("black_dog_front_door", "black_dog_front_door",
                "Black Dog", "American", "Urbana IL", "$$");
        addRestaurantMap("miga_front_door", "miga_front_door",
                "Miga", "Asian", "Champaign IL", "$$$");
        addRestaurantMap("v_picasso_front_door", "v_picasso_front_door",
                "V.Picasso", "American", "Champaign IL", "$$$");

    }


    public static void addRestaurant(String restaurantId, String name, String cuisine,
                              String address, String price)
    {
        Restaurant item = new Restaurant(restaurantId, name, cuisine, address, price);
        restaurantList.add(item);
    }

    public static void addRestaurantMap(String key, String restaurantId, String name, String cuisine,
                                        String address, String price)
    {
        Restaurant item = new Restaurant(restaurantId, name, cuisine, address, price);
        restaurantMap.put(key, item);
    }

}
