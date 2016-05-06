package com.example.zweng4.menubox;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ComparisonActivity extends AppCompatActivity {
    //private String[] mobileArray = {" "," "," "," "," "," "," "," "};
    private List<String> mobileArray = new ArrayList<>();
    private EditText compare_txt;
    //private ImageButton search_btn;
    private ImageView search_btn;
    private SQLiteDatabase sqLiteDatabase;

    private ListView listView;
    ListAdapter adapter;

    private String compare;
    private Cursor query;
    private int i;
    private int max;
    private String name;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);

        adapter = new CustomAdapter(this, mobileArray);
        compare_txt = (EditText)findViewById(R.id.compare_txt);
        //search_btn = (ImageButton)findViewById(R.id.search_btn);
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
        sqLiteDatabase.close();

        compare = compare_txt.getText().toString();

        max = 0;
        sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        query = sqLiteDatabase.rawQuery("SELECT * FROM restaurants" , null);
        if (query.moveToFirst()) {
            do  {//cursor is empty if there is no data, else move to the first data
                max ++;

            } while (query.moveToNext());
        }
        else Toast.makeText(getBaseContext(), "No restaurant match", Toast.LENGTH_LONG).show();
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
                //i++;
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
                //for(i = 0; i <8;i++)
                    //mobileArray[i] = " ";
                compare = compare_txt.getText().toString();
                max = 0;
                sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
                query = sqLiteDatabase.rawQuery("SELECT * FROM restaurants WHERE name = '" + compare + "'" , null);
                if (query.moveToFirst()) {
                    do  {//cursor is empty if there is no data, else move to the first data
                        max ++;

                    } while (query.moveToNext());
                }
                else Toast.makeText(getBaseContext(), "No restaurant match", Toast.LENGTH_LONG).show();
                sqLiteDatabase.close(); //count number of selected tuples.

                sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
                query = sqLiteDatabase.rawQuery("SELECT * FROM restaurants WHERE name = '" + compare + "'", null);//where is filled in to the second argument
                i = 0;

                if (query.moveToFirst()) {
                    do {//cursor is empty if there is no data, else move to the first data
                        //cycle through all records.
                        name = query.getString(0);//field number
                        location = query.getString(1);


                        //mobileArray[i] = name + "   " + location;
                        if(name != null && location != null)
                            mobileArray.add(name + "  " + location);
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
