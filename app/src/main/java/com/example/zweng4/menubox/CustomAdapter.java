package com.example.zweng4.menubox;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


class CustomAdapter extends ArrayAdapter<String>{
    CustomAdapter(Context context, List<String> mobileArray){
        super(context, R.layout.activity_listview,mobileArray);
    }
    private final List<Restaurant> restaurants = RestaurantDataProvider.restaurantList;
    public static String MENU_ID = "MENU_ID";

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.activity_listview, parent, false);

        String singleItem = getItem(position);
        TextView textView = (TextView) customView.findViewById(R.id.item_text);
        ImageButton button = (ImageButton)customView.findViewById(R.id.item_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(getContext(),"Restaurant removed from list",Toast.LENGTH_SHORT).show();
            }
        });

        int i = 0;
        for(i=0;i<restaurants.size();i++) {
            if(singleItem.equalsIgnoreCase(restaurants.get(i).getName())){
                break;
            }
        }
        List<String> menus = RestaurantDataProvider.restaurantMenuMap.get(restaurants.get(i).getRestaurantId());
        final RestaurantMenuListAdapter menuListAdapter = new RestaurantMenuListAdapter(getContext(), R.layout.grid_item, menus);
        GridView gridview = (GridView)customView.findViewById(R.id.gridView);
        gridview.setAdapter(menuListAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Send intent to SingleViewActivity
                Intent intent = new Intent(getContext(), SingleViewActivity.class);

                // Pass image index
//                intent.putExtra("id", position);
//                intent.putExtra("menuList", );
                String menu_id = menuListAdapter.getMenuId(position);
                intent.putExtra(MENU_ID, menu_id);
                getContext().startActivity(intent);
            }
        });
        textView.setText(singleItem);
        return customView;
    }
}
