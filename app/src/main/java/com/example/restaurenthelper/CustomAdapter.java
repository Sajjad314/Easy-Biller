package com.example.restaurenthelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    List<String> menu , price= new ArrayList<String>();
    Context context;
    LayoutInflater inflater;

    CustomAdapter(Context context , List<String> menu ,List<String> price){
        this.context = context;
        this.menu = menu;
        this.price = price;
    }

    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view== null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sample_layout, viewGroup, false);
        }

        TextView menuText = view.findViewById(R.id.textViewSample);
        TextView priceText = view.findViewById(R.id.textViewSample2);

        menuText.setText(menu.get(i));
        priceText.setText(price.get(i));

        return view;
    }
}
