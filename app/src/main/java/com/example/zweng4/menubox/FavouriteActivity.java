package com.example.zweng4.menubox;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

 public class FavouriteActivity extends AppCompatActivity {
    //String[] mobileArray = {" "," "," ", " ", " ", " ", " ", " ", " ", " ", " "," "," "," "," "," "," ", " ", " "," ", " ",};
    private List<String> mobileArray = new ArrayList<>();
    private EditText fav_txt;
    private ImageView search_btn;
    private SQLiteDatabase sqLiteDatabase;

    private ListView listView;

    private ListAdapter adapter;
    private String fav;
    private Cursor query;
    private int i;
    private int max;
    private String name;
    private String location;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        adapter = new CustomAdapter(this, mobileArray);
        fav_txt = (EditText) findViewById(R.id.fav_txt);
        //search_btn = (ImageButton) findViewById(R.id.search_btn);
        search_btn = (ImageView)findViewById(R.id.search_btn);
        sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        //factory optional to instantiate a cursor when query is called.

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurants(name TEXT, location TEXT)");
        sqLiteDatabase.execSQL("DROP TABLE restaurants");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurants(name TEXT, location TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Cravings', '603 S Wright St');");

        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Dominos Pizza','1108 N Ave');");
        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Kofusion','701 S Gregory St');");
        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Sitara','114 S Race St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Cravings', '603 S Wright St');");
//
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Dominos Piazza','1108 N Cunningham Ave');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Kofusion','701 S Gregory St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Sitara','114 S Race St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Sakanaya','403 E Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Zorba','627 E Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Golden Work','405 E Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Seoul Taco','608 S 6th St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Spoon House','616 E Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Qdoba','1401 W Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Azzip Pizza','505 E Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Sushi Ichiban','619 S Wright St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Flat Top Grill','607 S 6th St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Mandarin Work','403 E Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Antonios Piazza','619 E Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Sitara','114 S Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Which Wich','512 E Green St');");
//        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Timpones','710 S W Goodwin Ave');");
        sqLiteDatabase.close();

        fav = fav_txt.getText().toString();

        max = 0;
        sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        query = sqLiteDatabase.rawQuery("SELECT * FROM restaurants", null);
        if (query.moveToFirst()) {
            do {//cursor is empty if there is no data, else move to the first data
                max++;

            } while (query.moveToNext());
        } else Toast.makeText(getBaseContext(), "No restaurant match", Toast.LENGTH_LONG).show();
        sqLiteDatabase.close(); //count number of selected tuples.

        sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        query = sqLiteDatabase.rawQuery("SELECT * FROM restaurants", null);//where is filled in to the second argument
        i = 0;

        if (query.moveToFirst()) {
            do {//cursor is empty if there is no data, else move to the first data
                //cycle through all records.
                name = query.getString(0);//field number
                location = query.getString(1);


                //mobileArray[i] = name + "   " + location;
                if(name != null && location != null)
                    mobileArray.add(name);
                i++;

            } while (query.moveToNext() && i < max);


        } else {
            Toast.makeText(getBaseContext(), "error retrieving data", Toast.LENGTH_LONG).show();

        }
        sqLiteDatabase.close();


        listView = (ListView) findViewById(R.id.xmlListView);
        listView.setAdapter(adapter);


        search_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                for (i = 0; i < 14; i++)
//                    mobileArray[i] = " ";
                fav = fav_txt.getText().toString();
                max = 0;
                sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
                query = sqLiteDatabase.rawQuery("SELECT * FROM restaurants WHERE name = '" + fav + "'", null);
                if (query.moveToFirst()) {
                    do {//cursor is empty if there is no data, else move to the first data
                        max++;

                    } while (query.moveToNext());
                } else
                    Toast.makeText(getBaseContext(), "No restaurant match", Toast.LENGTH_LONG).show();
                sqLiteDatabase.close(); //count number of selected tuples.

                sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
                query = sqLiteDatabase.rawQuery("SELECT * FROM restaurants WHERE name = '" + fav + "'", null);//where is filled in to the second argument
                i = 0;

                if (query.moveToFirst()) {
                    do {//cursor is empty if there is no data, else move to the first data
                        //cycle through all records.
                        name = query.getString(0);//field number
                        location = query.getString(1);


                        //mobileArray[i] = name + "   " + location;
                        if(name != null && location != null)
                            mobileArray.add(name);
                        i++;

                    } while (query.moveToNext() && i < max);


                } else {
                    Toast.makeText(getBaseContext(), "error retrieving data", Toast.LENGTH_LONG).show();

                }
                sqLiteDatabase.close();


                listView = (ListView) findViewById(R.id.xmlListView);
                listView.setAdapter(adapter);


            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        //Add intent for Phone
                        startActivity(new Intent("RestaurantActivity"));
                        break;
                    case 1:
                        //Add intent for Email
                        break;
                    case 2:
                        //Add intent for Website
                        break;
                    case 3:
                        //Add intent for Opening Time
                        break;

                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
    public void openOrCreateDatabase(String Name, String Address){
        openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurants(name TEXT, location TEXT)");
        sqLiteDatabase.execSQL("DROP TABLE restaurants");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurants(name TEXT, location TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('" + Name + "', '" + Address + "')");
        Toast.makeText(getBaseContext(), "error retrieving data", Toast.LENGTH_LONG).show();
        sqLiteDatabase.close();
        Toast.makeText(getBaseContext(), "Favourite added", Toast.LENGTH_LONG).show();


    };

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
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Favourite Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.zweng4.menubox/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Favourite Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.zweng4.menubox/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
