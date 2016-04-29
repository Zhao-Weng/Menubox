package com.example.zweng4.menubox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


class CustomAdapter extends ArrayAdapter<String>{
    CustomAdapter(Context context, String[] mobileArray){
        super(context, R.layout.activity_listview,mobileArray);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.activity_listview, parent, false);

        String singleItem = getItem(position);
        TextView textView = (TextView) customView.findViewById(R.id.item_text);
        Button button = (Button)customView.findViewById(R.id.item_btn);
        textView.setText(singleItem);
        return customView;
    }
}
