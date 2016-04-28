package com.example.zweng4.menubox;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private float mThumbWidthdp = 90;
    private float mThumbHeightdp = 100;
    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            int mThumbWidthPixel = convertDpToPixels(mThumbWidthdp, mContext);
            int mThumbHeightPixel = convertDpToPixels(mThumbHeightdp, mContext);
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(mThumbWidthPixel, mThumbHeightPixel));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    //convert desire dp to pixels
    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );
    }
    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.m1, R.drawable.m2,
            R.drawable.m_3, R.drawable.m_4,
            R.drawable.m_5, R.drawable.m_6
    };
}