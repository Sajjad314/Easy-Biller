package com.example.restaurenthelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SalesActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView  bill ,billPrice , totalPrice;
    private ListView listView;
    private AppCompatButton reset, delete;
    double t_price=0;
    boolean flag =false;
    public List<String> menu = new ArrayList<>();
    public List<String> price = new ArrayList<>();
    public String prevMenu = "";
    String id;
    ScrollView scrollView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        MyDBHandler db = MyDBHandler.getInstance(this);

        listView = findViewById(R.id.listViewId);
        bill= findViewById(R.id.textViewBill);
        scrollView = findViewById(R.id.scrollView);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        if(prevMenu.length() != 0){
            bill.append(prevMenu);
            //Toast.makeText(SalesActivity.this, bill.getText().toString(), Toast.LENGTH_SHORT).show();
        }

        billPrice = findViewById(R.id.textViewPrice);
        totalPrice =findViewById(R.id.textViewTotalPrice);

//        menu = getResources().getStringArray(R.array.menu_item);
//        price = getResources().getStringArray(R.array.item_price);
        List<Model> modelList = db.getAllContacts();

        int t=0;
        for(Model model : modelList){
           // Toast.makeText(this, model.getProduct(), Toast.LENGTH_SHORT).show();
            if(model.getProduct() != null){
            menu.add(model.getProduct());
            price.add(model.getPrice());}
        }
        //Toast.makeText(this, menu[0], Toast.LENGTH_SHORT).show();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String value = bundle.getString("price");
            if(value.length()!=0){
                t_price = Double.parseDouble(value);
            }

            String value2 = bundle.getString("menu");
            String value3 = bundle.getString("priceList");
            id = bundle.getString("id");
            totalPrice.setText(value);
            bill.append(value2);
            billPrice.append(value3);
        }

        reset = findViewById(R.id.reset);
        delete = findViewById((R.id.delete));

        reset.setOnClickListener(this);
        delete.setOnClickListener(this);







        CustomAdapter adapter = new CustomAdapter(this , menu , price);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bill.append("\n"+ menu.get(i));
                prevMenu = prevMenu+"\n"+ menu.get(i);
                //Toast.makeText(SalesActivity.this, prevMenu, Toast.LENGTH_SHORT).show();
                billPrice.append("\n"+ price.get(i));
                t_price = t_price+Double.parseDouble(price.get(i));
                totalPrice.setText(t_price+"Taka");
                flag = true;
            }
        });




    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        //if (t_price != 0) {
            Bundle extras = new Bundle();
            extras.putString("check", String.valueOf(t_price));
            extras.putString("menu", bill.getText().toString());
            extras.putString("priceList",billPrice.getText().toString());
            extras.putString("id", id);

            //intent.putExtras(extras);
            //Toast.makeText(SalesActivity.this,"true", Toast.LENGTH_SHORT).show();
        if (t_price != 0) {
            extras.putString("flag", "1");
            intent.putExtras(extras);
            setResult(RESULT_OK, intent);
            finish();
        }
       // }
        else{
            extras.putString("flag", "-1");
            intent.putExtras(extras);
            setResult(RESULT_OK, intent);
            finish();
        }


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.reset){
            String table_id = id;
            if(bill.getText().toString().length() != 0 && billPrice.getText().toString().length() != 0 && totalPrice.getText().toString().length() != 0){
                MyDBHandler db = MyDBHandler.getInstance(this);
                db.addBill(table_id,bill.getText().toString(),billPrice.getText().toString(),totalPrice.getText().toString());
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
            }
            bill.setText("");
            billPrice.setText("");
            totalPrice.setText("");
            t_price = 0;
        }
        if(view.getId() == R.id.delete){
            String temp = bill.getText().toString();
            temp = temp.substring(0,temp.lastIndexOf("\n"));
            bill.setText(temp);
            String temp2 = billPrice.getText().toString();
            String[] arrOfStr = temp2.split("\n", -2);
            temp2 = temp2.substring(0,temp2.lastIndexOf("\n"));
            billPrice.setText(temp2);

            String trimmedString = temp2.trim();
            //nt price= Integer.parseInt(temp2);
            int l = arrOfStr.length;
            String priceStr = arrOfStr[l-1];
            Double price = Double.parseDouble(priceStr);
            t_price = t_price-price;
            totalPrice.setText(t_price+"Taka");
            //Toast.makeText(SalesActivity.this,price, Toast.LENGTH_SHORT).show();
        }
    }


}