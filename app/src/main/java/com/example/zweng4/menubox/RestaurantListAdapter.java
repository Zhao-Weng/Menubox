package com.example.zweng4.menubox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by huckzou on 4/29/16.
 */
public class RestaurantListAdapter extends ArrayAdapter<Restaurant>{

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

        Restaurant restaurant = restaurants.get(position);

        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        nameText.setText(restaurant.getName());

        return convertView;
    }
}
