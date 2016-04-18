package com.example.zweng4.menubox;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Hashtable;
import java.util.Map;

public class SearchableActivity extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Map<String, String> map = new Hashtable<>();
        map.put("Chinese", "Cravings");
        map.put("Italian", "Mia Za's");
        map.put("Fast Food", "McDonald's");
        map.put("Fancy", "Steak House");
        String result = "found nothing";
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.i("search", "query=" + query);
            String str = map.get(query);
            if (str != null) {
                result = "result: " + query + " is " + str;
            }
        }
        TextView text = new TextView(this);
        text.setText(result);
        setContentView(text);

//        handleIntent(getIntent());


//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//        handleIntent(intent);
//    }
//
//    private void handleIntent(Intent intent) {
//
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//
//            showResults(query);
//        }
//    }
//
//    private void showResults(String query) {
//        // Query your data set and show results
//        // ...
//
//    }
    }
}

