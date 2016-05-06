package com.example.zweng4.menubox;

/**
 * Created by zweng on 4/21/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SingleViewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_view);

        // Selected image id
        String menu_id = getIntent().getStringExtra(RestaurantActivity.MENU_ID);
        ImageView iv = (ImageView) findViewById(R.id.SingleView);
        Bitmap bitmap = getBitmapFromAsset(menu_id);
        iv.setImageBitmap(bitmap);
        iv.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eid = event.getAction();
                switch(eid) {
                    case MotionEvent.ACTION_DOWN:
                        finish();
                        break;
                }
                return true;
            }
        });
    }
    private Bitmap getBitmapFromAsset(String menuId)
    {
        AssetManager assetManager = getAssets();
        InputStream stream = null;

        try{
            stream = assetManager.open("restaurantMenu/"+ menuId + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}