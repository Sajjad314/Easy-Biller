package com.example.restaurenthelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowBill extends AppCompatActivity implements View.OnClickListener{
    AppCompatButton show;
    List<String>tableId = new ArrayList<>();
    List<String> totalProduct = new ArrayList<>();
    List<String> priceList = new ArrayList<>();
    List<String> totalPrice = new ArrayList<>();
    List<String> date = new ArrayList<>();
    ListView listView;
    EditText dateText;
    TextView empty;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bill);

        listView = findViewById(R.id.listViewId);
        show = findViewById(R.id.show);
        dateText = findViewById(R.id.date);
        empty = findViewById(R.id.empty);

        show.setOnClickListener(this);

//        CustomAdapterBill adapter = new CustomAdapterBill(this,tableId, totalProduct,priceList,totalPrice,date);
//        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        listView.setEmptyView(empty);
        MyDBHandler db = MyDBHandler.getInstance(this);
        String searchDate = dateText.getText().toString();
        if(dateText.length() == 0){
            Toast.makeText(this, "Enter a Date", Toast.LENGTH_SHORT).show();
        }
        List<BillModel> billList = db.getAllBills(searchDate);
        for(BillModel bill : billList){
            tableId.add(bill.getTableId());
            totalProduct.add(bill.getTotalProduct());
            priceList.add(bill.getPriceList());
            totalPrice.add(bill.getTotalPrice());
            date.add(bill.getDate());
        }
        //Toast.makeText(this, totalPrice.get(1), Toast.LENGTH_SHORT).show();
        CustomAdapterBill adapter = new CustomAdapterBill(this,tableId, totalProduct,priceList,totalPrice,date);
        listView.setAdapter(adapter);
    }
}