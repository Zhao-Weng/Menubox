package com.example.zweng4.menubox;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {
    public static String MENU_ID = "MENU_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String restaurantId = getIntent().getStringExtra(SearchActivity.RESTAURANT_ID);
        Restaurant restaurant = RestaurantDataProvider.restaurantMap.get(restaurantId);
        TextView tv = (TextView) findViewById(R.id.restaurantMainPageName);
        tv.setText(restaurant.getName());

        TextView nameText = (TextView) findViewById(R.id.restaurantMainPageName);
        nameText.setText(restaurant.getName());
        TextView addressText = (TextView) findViewById(R.id.restaurantMainPageAddress);
        addressText.setText(restaurant.getAddress());
        TextView priceText = (TextView) findViewById(R.id.restaurantMainPagePrice);
        priceText.setText(restaurant.getPrice());

        ImageView iv = (ImageView) findViewById(R.id.restaurantMainPageImage);
        Bitmap bitmap = getBitmapFromAsset(restaurant.getRestaurantId());
        iv.setImageBitmap(bitmap);


        //get the gridView
        List<String> menus = RestaurantDataProvider.restaurantMenuMap.get(restaurantId);
        final RestaurantMenuListAdapter menuListAdapter = new RestaurantMenuListAdapter(this, R.layout.grid_item, menus);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(menuListAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Send intent to SingleViewActivity
                Intent intent = new Intent(getApplicationContext(), SingleViewActivity.class);

                // Pass image index
//                intent.putExtra("id", position);
//                intent.putExtra("menuList", );
                String menu_id = menuListAdapter.getMenuId(position);
                intent.putExtra(MENU_ID, menu_id);
                startActivity(intent);
            }
        });
        GridView other_grid_view = (GridView) findViewById(R.id.gridView2);
        other_grid_view.setAdapter(menuListAdapter);
        other_grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Send intent to SingleViewActivity
                Intent intent = new Intent(getApplicationContext(), SingleViewActivity.class);

                // Pass image index

                String menu_id = menuListAdapter.getMenuId(position);
                intent.putExtra(MENU_ID, menu_id);
                startActivity(intent);
            }
        });
        ImageView upvote_most_popular = (ImageView)findViewById(R.id.imageButton);
        upvote_most_popular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(RestaurantActivity.this,"Menu Upvoted",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView upvote_other_munu = (ImageView)findViewById(R.id.imageButton3);
        upvote_other_munu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(RestaurantActivity.this,"Menu Upvoted",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView downvote_most_popular = (ImageView)findViewById(R.id.imageButton2);
        downvote_most_popular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(RestaurantActivity.this,"Menu Downvoted",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView downvote_other_menu = (ImageView)findViewById(R.id.imageButton4);
        downvote_other_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(RestaurantActivity.this,"Menu Downvoted",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView expand_most_popular = (ImageView)findViewById(R.id.imageButton5);
        expand_most_popular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(RestaurantActivity.this,"Rest of Images Shown",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView expand_other_menu = (ImageView)findViewById(R.id.imageButton6);
        expand_other_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(RestaurantActivity.this,"Rest of Images Shown",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //when click the image button
    public void jumpToUploadPage(View view){
        Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
        startActivity(intent);
    }

    private Bitmap getBitmapFromAsset(String restaurantId)
    {
        AssetManager assetManager = getAssets();
        InputStream stream = null;

        try{
            stream = assetManager.open("frontDoor/" + restaurantId + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        switch (id) {

            case R.id.action_favorite:
                intent = new Intent(this, FavouriteActivity.class);
                //use the class name, can change the referred name in the manifest but still
                //get to the correct class
                startActivity(intent);
                return true;

            case R.id.action_compare:
                intent = new Intent(this, ComparisonActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_account:
                return true;

            case R.id.action_settings:
                return true;

            case R.id.action_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


