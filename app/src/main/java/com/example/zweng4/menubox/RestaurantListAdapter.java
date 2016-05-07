package com.example.zweng4.menubox;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by huckzou on 4/29/16.
 */
public class RestaurantListAdapter extends ArrayAdapter<Restaurant>{
    private SQLiteDatabase sqLiteDatabase;
    private List<Restaurant> restaurants;
    public RestaurantListAdapter(Context context, int resource, List<Restaurant> objects) {
        super(context, resource, objects);
        restaurants = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        final Restaurant restaurant = restaurants.get(position);

        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        nameText.setText(restaurant.getName());
        TextView cuisineText = (TextView) convertView.findViewById(R.id.cuisineText);
        cuisineText.setText(restaurant.getCuisine());
        TextView addressText = (TextView) convertView.findViewById(R.id.addressText);
        addressText.setText(restaurant.getAddress());
        TextView priceText = (TextView) convertView.findViewById(R.id.priceText);
        priceText.setText(restaurant.getPrice());

        ImageView iv = (ImageView) convertView.findViewById(R.id.restaurantImageView);
        Bitmap bitmap = getBitmapFromAsset(restaurant.getRestaurantId());
        iv.setImageBitmap(bitmap);   //set bit map for iv.

        ImageView fav_view = (ImageView) convertView.findViewById(R.id.favoriteIcon);
        ImageView com_view = (ImageView) convertView.findViewById((R.id.compareIcon));



       fav_view.setOnClickListener(new

                                           View.OnClickListener()

                                           {

                                               @Override
                                               public void onClick(View v) {
                                                   Toast.makeText(getContext(), "Favorite added successfully", Toast.LENGTH_SHORT).show();

                                                  }
                                              }

        );
        com_view.setOnClickListener(new

                                            View.OnClickListener()

                                            {

                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(getContext(), "Compare item added successfully", Toast.LENGTH_SHORT).show();

                                                }
                                            }

        );

        return convertView;
    }

    private Bitmap getBitmapFromAsset(String restaurantId)
    {
        AssetManager assetManager = getContext().getAssets();   //set up assetManager
        InputStream stream = null;

        try{
            stream = assetManager.open("frontDoor/" + restaurantId + ".png");   //use asset Manager to open the file and create stream
            return BitmapFactory.decodeStream(stream);     //decode the stream
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


}
