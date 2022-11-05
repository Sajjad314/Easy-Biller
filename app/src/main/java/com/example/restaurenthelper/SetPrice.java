package com.example.restaurenthelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SetPrice extends AppCompatActivity implements View.OnClickListener{
    EditText product, price;
    AppCompatButton add;
    String pro, pri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_price);
        product = findViewById(R.id.product);
        price = findViewById(R.id.price);
        add = findViewById(R.id.add);


        add.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add){
            MyDBHandler db = MyDBHandler.getInstance(this);
            pro = product.getText().toString();
            pri = price.getText().toString();
            if(pro.length() == 0 || pri.length() == 0){
                Toast.makeText(this, "Please insert Product Or price", Toast.LENGTH_SHORT).show();
            } else{
                db.addContact(pro, pri);
                price.setText("");
                product.setText("");
                Toast.makeText(this, pro+"+"+pri, Toast.LENGTH_SHORT).show();
            }


        }
    }
}