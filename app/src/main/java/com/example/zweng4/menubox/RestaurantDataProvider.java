package com.example.zweng4.menubox;

import java.util.ArrayList;
import java.util.Arrays;
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
    public static Map<String, List<String>> restaurantMenuMap = new HashMap<>();
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
        addRestaurantMenu("cravings_front_door", new String[]{"cravings_menu_1", "cravings_menu_2"});
        addRestaurantMenu("black_dog_front_door", new String[]{"black_dog_menu_1", "black_dog_menu_2", "black_dog_menu_3"});
        addRestaurantMenu("dominos_front_door", new String[]{"dominos_menu_1", "dominos_menu_2", "dominos_menu_3"});
        addRestaurantMenu("kofusion_front_door", new String[]{"kofusion_menu_1", "kofusion_menu_2", "kofusion_menu_3"});
        addRestaurantMenu("miga_front_door", new String[]{"miga_menu_1", "miga_menu_2", "miga_menu_3"});
        addRestaurantMenu("mia_zas_front_door", new String[]{"mia_zas_menu_1"});
        addRestaurantMenu("sakanaya_front_door", new String[]{"sakanaya_menu_1"});
        addRestaurantMenu("sitara_front_door", new String[]{"sitara_menu_1", "sitara_menu_2", "sitara_menu_3"});
        addRestaurantMenu("v_picasso_front_door", new String[]{"v_picasso_menu_1","v_picasso_menu_2","v_picasso_menu_3","v_picasso_menu_4"});

    }


    public static void addRestaurant(String restaurantId, String name, String cuisine,
                              String address, String price)
    {
        Restaurant item = new Restaurant(restaurantId, name, cuisine, address, price);
        restaurantList.add(item);
        restaurantMap.put(restaurantId, item);
    }

    public static void addRestaurantMenu(String restaurantId,  String[] menus)
    {
        List<String> menuList = new ArrayList<String>(Arrays.asList(menus));
        restaurantMenuMap.put(restaurantId, menuList);
    }

}
