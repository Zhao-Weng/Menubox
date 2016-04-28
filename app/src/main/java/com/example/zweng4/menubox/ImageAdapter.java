package com.example.zweng4.menubox;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
//    private Context mContext;
//    private float mThumbWidthdp = 90;
//    private float mThumbHeightdp = 100;
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;
    // Constructor
    public ImageAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item(R.drawable.m1));
        mItems.add(new Item(R.drawable.m2));
        mItems.add(new Item(R.drawable.m_3));
        mItems.add(new Item(R.drawable.m_4));
        mItems.add(new Item(R.drawable.m_5));
        mItems.add(new Item(R.drawable.m_6));

        //mContext = context;
    }

    public int getCount() {

        //return mThumbIds.length;
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return mItems.get(position).drawableId;
        //return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//
//        if (convertView == null) {
//            int mThumbWidthPixel = convertDpToPixels(mThumbWidthdp, mContext);
//            int mThumbHeightPixel = convertDpToPixels(mThumbHeightdp, mContext);
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(mThumbWidthPixel, mThumbHeightPixel));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setBackgroundResource(R.drawable.grid_item_corner);
//            //imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//        imageView.setImageResource(mThumbIds[position]);
//
//        return imageView;
        View v = convertView;
        ImageView picture;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, parent, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);

        Item item = (Item) getItem(position);

        picture.setImageResource(item.drawableId);

        return v;
    }
    //convert desire dp to pixels
//    public static int convertDpToPixels(float dp, Context context){
//        Resources resources = context.getResources();
//        return (int) TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP,
//                dp,
//                resources.getDisplayMetrics()
//        );
//    }
    // Keep all Images in array
//    public Integer[] mThumbIds = {
//            R.drawable.m1, R.drawable.m2,
//            R.drawable.m_3, R.drawable.m_4,
//            R.drawable.m_5, R.drawable.m_6
//    };

    public static class Item {
        public final int drawableId;

        Item(int drawableId) {
            this.drawableId = drawableId;
        }
    }
}