package com.example.zweng4.menubox;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity {
//    private String restaurants[] = {"Cravings","Dominos Pizza","Mia Zas","Kofusion","Sitara"};
    ListView listView;
    private final List<Restaurant> restaurants = RestaurantDataProvider.restaurantList;
    public static String RESTAURANT_ID = "RESTAURANT_ID";
    public List<Restaurant> results;

    private SQLiteDatabase sqLiteDatabase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list);
        Bundle extras = getIntent().getExtras();
        if (extras != null ) {
            String query_content = extras.getString("query_content");
            //call query function directly.
            if(query_content != null)
                doMySearch(query_content);
        } else {
            handleIntent(getIntent());
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(results.size() != 0) {
                    Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                    //use the class name, can change the referred name in the manifest but still
                    //get to the correct class
                    Restaurant restaurant = results.get(position);
                    intent.putExtra(RESTAURANT_ID, restaurant.getRestaurantId());
                    startActivity(intent);
                }
            }
        });
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
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if(query != null)
                doMySearch(query);
        }
    }

    private void doMySearch(String query){
        results = new ArrayList<>();
        for(Restaurant item: restaurants)
        {
            String lowerCaseQuery = query.toLowerCase();
            //if the query matches any of the restaurant information (including name, address, etc.
            //we will add it to our result.
            if(item.getName().toLowerCase().contains(lowerCaseQuery) ||
                item.getAddress().toLowerCase().contains(lowerCaseQuery) ||
                    item.getCuisine().toLowerCase().contains(lowerCaseQuery) ||
                    item.getPrice().toLowerCase().contains(lowerCaseQuery)){

                results.add(item);
            }
        }
        if(results.size() == 0)
        {
            String[] notFound = new String[1];
            notFound[0] = new String("No Restaurant Found");

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.no_list_item, notFound);
            listView.setAdapter(adapter);
        }
        else {
            //Restaurant[] finalResults = results.toArray(new Restaurant[results.size()]);
            RestaurantListAdapter adapter = new RestaurantListAdapter(
                    this, R.layout.list_item, results);
            listView.setAdapter(adapter);
        }
    }

    public void addToFavoriteList(View v){
        Toast.makeText(SearchActivity.this,"Restaurant Added to Favorite List",Toast.LENGTH_SHORT).show();
        sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurants(name TEXT, location TEXT)");
        sqLiteDatabase.execSQL("DROP TABLE restaurants");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurants(name TEXT, location TEXT)");
        //create a brand new table
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (results.size() != 0) {
                    Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                    //use the class name, can change the referred name in the manifest but still
                    //get to the correct class
                    Restaurant restaurant = results.get(position);
                    sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('" + restaurant.getName() + "', '" + restaurant.getAddress() + "')");
                }
            }
        });

    }
    public void addToCompareList(View v){
        Toast.makeText(SearchActivity.this,"Restaurant Added to Compare List",Toast.LENGTH_SHORT).show();
        sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurants(name TEXT, location TEXT)");
        sqLiteDatabase.execSQL("DROP TABLE restaurants");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurants(name TEXT, location TEXT)");
        //create a brand new table
    }
}
