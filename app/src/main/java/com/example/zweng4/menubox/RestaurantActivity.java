package com.example.zweng4.menubox;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class RestaurantActivity extends AppCompatActivity {

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
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Send intent to SingleViewActivity
                Intent i = new Intent(getApplicationContext(), SingleViewActivity.class);

                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
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
            stream = assetManager.open(restaurantId + ".png");
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


