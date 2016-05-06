package com.example.zweng4.menubox;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by huckzou on 5/5/16.
 */
public class RestaurantMenuListAdapter extends ArrayAdapter<String>{

    private List<String> restaurant_menus;
    public RestaurantMenuListAdapter(Context context, int resource, List<String> objects){
        super(context, resource, objects);
        restaurant_menus = objects;
    }

    public int getCount() {
        return restaurant_menus.size();
    }

    public String getMenuId(int position) {
        return restaurant_menus.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.grid_item, parent, false);
        }
        String menu = restaurant_menus.get(position);
        ImageView iv = (ImageView) convertView.findViewById(R.id.picture);
        Bitmap bitmap = getBitmapFromAsset(menu);
        iv.setImageBitmap(bitmap);

        return convertView;
    }

    private Bitmap getBitmapFromAsset(String menuId)
    {
        AssetManager assetManager = getContext().getAssets();
        InputStream stream = null;

        try{
            stream = assetManager.open("restaurantMenu/"+ menuId + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
