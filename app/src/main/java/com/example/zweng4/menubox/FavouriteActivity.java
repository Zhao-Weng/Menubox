package com.example.zweng4.menubox;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FavouriteActivity extends AppCompatActivity {
    String[] mobileArray = {" "," "," "," "," "," "," "," "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-2.db", MODE_PRIVATE, null);
        //factory optional to instantiate a cursor when query is called.

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurants(name TEXT, location TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Cravings', '603 S Wright St');");

        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Dominos Piazza','1108 N Cunningham Ave');");
        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Kofusion','701 S Gregory St');");
        sqLiteDatabase.execSQL("INSERT INTO restaurants VALUES('Sitara','114 S Race St');");
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM restaurants", null);//where is filled in to the second argument
        int i = 0;
        String name;
        String location;
        if (query.moveToFirst()) {
            do {//cursor is empty if there is no data, else move to the first data
                //cycle through all records.
                name = query.getString(0);//field number
                location = query.getString(1);


                mobileArray[i] = name + "   "+ location;
                if(i <= 6)
                    i++;

            } while (query.moveToNext());


        }
        else{
            Toast.makeText(getBaseContext(), "error retrieving data", Toast.LENGTH_LONG).show();

        }
        sqLiteDatabase.close();


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.xmlListView);
        listView.setAdapter(adapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
