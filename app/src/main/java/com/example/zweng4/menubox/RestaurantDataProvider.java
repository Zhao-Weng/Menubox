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

        addRestaurant("restaurant101",
                "Cravings", "Chinese", "Champaign IL", "Cheap");
        addRestaurant("restaurant102",
                "Dominos Pizza", "Italian", "Champaign IL", "Cheap");
        addRestaurant("restaurants103",
                "Mia Zas", "Italian", "Champaign IL", "Cheap");
        addRestaurant("restaurants104",
                "Kofusion", "Korean", "Champaign IL", "Cheap");
        addRestaurant("restaurants105",
                "Sitari", "Indian", "Urbana IL", "Cheap");
    }

    public static void addRestaurant(String restaurantId, String name, String cuisine,
                              String address, String price)
    {
        Restaurant item = new Restaurant(restaurantId, name, cuisine, address, price);
        restaurantList.add(item);
    }
}
