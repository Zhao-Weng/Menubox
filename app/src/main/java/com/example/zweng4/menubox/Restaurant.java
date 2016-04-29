package com.example.zweng4.menubox;

/**
 * Created by huckzou on 4/29/16.
 */
public class Restaurant {

    private String restaurantId;
    private String name;
    private String cuisine;
    private String address;
    private String price;

    public String getRestaurantId() { return restaurantId;}

    public String getName() { return name;}

    public String getCuisine() { return cuisine;}

    public String getAddress() { return address;}

    public String getPrice() {return price;}

    public Restaurant(String restaurantId, String name, String cuisine,
                      String address, String price) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisine = cuisine;
        this.address = address;
        this.price = price;
    }
}
