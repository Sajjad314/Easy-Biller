package com.example.restaurenthelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterBill extends BaseAdapter {

    List<String> tableID = new ArrayList<>();
    List<String>TotalProduct = new ArrayList<>();
    List<String> PriceList = new ArrayList<>();
    List<String> TotalPrice = new ArrayList<>();
    List<String> Date = new ArrayList<String>();
    Context contextt;
    LayoutInflater inflater;

    CustomAdapterBill(Context context, List<String> tableId, List<String> totalProduct, List<String> priceList, List<String> totalPrice, List<String> date){
       contextt = context;
       tableID = tableId;
       TotalProduct = totalProduct;
       PriceList = priceList;
       TotalPrice = totalPrice;
       Date = date;
        //Toast.makeText(contextt, tableID.get(0), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return tableID.size();
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
            inflater = (LayoutInflater) contextt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.bill_list, viewGroup, false);
        }
        if(!tableID.get(i).equals('0')){


            TextView tableno = view.findViewById(R.id.tableNo);
            TextView productlist = view.findViewById(R.id.textViewProductLict);
            TextView pricelist = view.findViewById(R.id.textViewPriceList);
            TextView totalprice = view.findViewById(R.id.textViewTotalPrice);
            TextView date = view.findViewById(R.id.date);
            Toast.makeText(contextt, tableID.get(i), Toast.LENGTH_SHORT).show();
            int tableid = Integer.parseInt(tableID.get(i))+1;
            tableno.setText("Table No: "+tableid);
            productlist.setText("products " +TotalProduct.get(i));
            pricelist.setText("Price: "+PriceList.get(i));
            totalprice.setText("Total Price: \n "+TotalPrice.get(i));
            date.setText("DATE: "+Date.get(i));

        }


        return view;
    }

}
