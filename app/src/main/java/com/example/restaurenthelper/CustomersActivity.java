package com.example.restaurenthelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CustomersActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton button[] = new AppCompatButton[8];
    private AppCompatButton commonButton;
    String[] price = new String[9], menu = new String[8], priceList = new String[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        for (int i = 0; i < 8; i++) {
            price[i] = new String("");
            menu[i] = new String("");
            priceList[i] = new String("");
        }

        button[0] = findViewById(R.id.table1);
        button[1] = findViewById(R.id.table2);
        button[2] = findViewById(R.id.table3);
        button[3] = findViewById(R.id.table4);
        button[4] = findViewById(R.id.table5);
        button[5] = findViewById(R.id.table6);
        button[6] = findViewById(R.id.table7);
        button[7] = findViewById(R.id.table8);
       // button[8] = findViewById(R.id.table8);

        for (int i = 0; i < 8; i++) {
            button[i].setOnClickListener(this);
        }
//        button1.setOnClickListener(this);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//        button4.setOnClickListener(this);
//        button5.setOnClickListener(this);
//        button6.setOnClickListener(this);
//        button7.setOnClickListener(this);
//        button8.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.table1) {
//            //button1.setBackgroundColor(getResources().getColor(R.color.button_click));
//            Intent intent = new Intent(MainActivity.this, SalesActivity.class);
//            Bundle extras = new Bundle();
//            int id = 1;
//            extras.putString("price",b1Price);
//            extras.putString("menu",b1Menu);
//            extras.putString("priceList", b1PriceList);
//            extras.putInt("id", id);
//            intent.putExtras(extras);
//            commonButton = button[0];
//            startActivityForResult(intent, 1);
//
//
//        }
        for (int i = 0; i < 8; i++) {
            if (view.getId() == button[i].getId()) {
                Intent intent = new Intent(this, SalesActivity.class);
                Bundle extras = new Bundle();
                //int id = button[i].getId();
                extras.putString("price", price[i]);
                extras.putString("menu", menu[i]);
                extras.putString("priceList", priceList[i]);
                extras.putString("id", String.valueOf(i));
                intent.putExtras(extras);
                //commonButton = button[0];
                startActivityForResult(intent, 1);
            }
        }
//        if (view.getId() == R.id.table2) {
//            button[1].setBackgroundColor(getResources().getColor(R.color.button_click));
//            Intent intent = new Intent(MainActivity.this, SalesActivity.class);
//            commonButton = button[1];
//            startActivityForResult(intent,1);
//        }
//        if (view.getId() == R.id.table3) {
//            Intent intent = new Intent(MainActivity.this, SalesActivity.class);
//            commonButton = button[3];
//            startActivityForResult(intent,1);
//        }
//        if (view.getId() == R.id.table4) {
//            Intent intent = new Intent(MainActivity.this, SalesActivity.class);
//            commonButton = button[4];
//            startActivityForResult(intent,1);
//        }
//        if (view.getId() == R.id.table5) {
//            Intent intent = new Intent(MainActivity.this, SalesActivity.class);
//            commonButton = button[5];
//            startActivityForResult(intent,1);
//        }
//        if (view.getId() == R.id.table6) {
//            Intent intent = new Intent(MainActivity.this, SalesActivity.class);
//            commonButton = button[6];
//            startActivityForResult(intent,1);
//        }
//        if (view.getId() == R.id.table7) {
//            Intent intent = new Intent(MainActivity.this, SalesActivity.class);
//            commonButton = button7;
//            startActivityForResult(intent,1);
//        }
//        if (view.getId() == R.id.table8) {
//            Intent intent = new Intent(MainActivity.this, SalesActivity.class);
//            commonButton = button8;
//            startActivityForResult(intent,1);
//        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                String check = data.getStringExtra("check");
                String menuR = data.getStringExtra("menu");
                String priceListR = data.getStringExtra("priceList");
                String stringId = data.getStringExtra("id");

                int id = Integer.parseInt(stringId);
                Toast.makeText(CustomersActivity.this, "id" + id, Toast.LENGTH_SHORT).show();
                if (data.getStringExtra("flag").equals("-1")) {
                    price[id] = check;
                    menu[id] = menuR;
                    priceList[id] = priceListR;
                    button[id].setBackgroundColor(getResources().getColor(R.color.teal_700));

                } else {
                    price[id] = check;
                    menu[id] = menuR;
                    priceList[id] = priceListR;
                    button[id].setBackgroundColor(getResources().getColor(R.color.button_click));
                }
            }
        }
    }
}
