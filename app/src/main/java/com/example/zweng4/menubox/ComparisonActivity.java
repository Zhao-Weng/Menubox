package com.example.zweng4.menubox;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ComparisonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);
        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        //factory optional to instantiate a cursor when query is called.
        sqLiteDatabase.execSQL("DROP Table contacts");
        sqLiteDatabase.execSQL("CREATE TABLE contacts(name TEXT, phone INTEGER, email TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('tim',434654654,'tim@email.com');");

        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Fred',434133654,'fred@email.com');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('tit',434654654,'tim@email.com');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('tiu',434654654,'tim@email.com');");
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM contacts", null);//where is filled in to the second argument
        if (query.moveToFirst()) {
            do {//cursor is empty if there is no data, else move to the first data
                //cycle through all records.
                String name = query.getString(0);//field number
                int phone = query.getInt(1);
                String email = query.getString(2);
                Toast.makeText(getBaseContext(), "Name =  " + name + " phone =" + phone + " email = " + email, Toast.LENGTH_LONG).show();
            } while (query.moveToNext());


        }
        else{
            Toast.makeText(getBaseContext(), "error retrieving data", Toast.LENGTH_LONG).show();

        }
        sqLiteDatabase.close();
    }
}
