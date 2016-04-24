package com.example.zweng4.menubox;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    private Button login_btn;
    private Button signin_btn;
    private Button res;
    private int LOG_FLAG = 0;
    private static final int MENU_ITEM_LOGOUT = 1001;
    private static final int MENU_ITEM_LOGIN = 1002;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        signin_btn = (Button) findViewById(R.id.signin_button);
        login_btn = (Button) findViewById(R.id.login_button);

        Typeface myTypeFace = Typeface.createFromAsset(getAssets(), "fonts/orange_juice.ttf");
        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setTypeface(myTypeFace);

        res = (Button)findViewById(R.id.res_button);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        signin_btn.setOnClickListener(new

                                              View.OnClickListener()

                                              {

                                                  @Override
                                                  public void onClick(View v) {
                                                      startActivity(new Intent("signInActivity"));
                                                  }
                                              }

        );


        login_btn.setOnClickListener(new

                                             View.OnClickListener()

                                             {

                                                 @Override
                                                 public void onClick(View v) {
                                                     startActivity(new Intent("logInActivity"));
                                                 }
                                             }

        );
        res.setOnClickListener(new

                                             View.OnClickListener()

                                             {

                                                 @Override
                                                 public void onClick(View v) {
                                                     startActivity(new Intent("RestaurantActivity"));
                                                 }
                                             }

        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
            case R.id.action_search:
                return true;

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

            case MENU_ITEM_LOGIN:
                LOG_FLAG = 1;
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;

            case MENU_ITEM_LOGOUT:
                LOG_FLAG = 0;
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
                "Main Page", // TODO: Define a title for the content shown.
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
                "Main Page", // TODO: Define a title for the content shown.
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
