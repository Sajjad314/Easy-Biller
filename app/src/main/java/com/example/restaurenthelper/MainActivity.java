package com.example.restaurenthelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton table, setMenu, showBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHandler db = MyDBHandler.getInstance(this);
        SQLiteDatabase sql = db.getWritableDatabase();

        table = findViewById(R.id.start);
        setMenu = findViewById(R.id.setMenu);
        showBill = findViewById(R.id.bill);

        table.setOnClickListener(this);
        setMenu.setOnClickListener(this);
        showBill.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.start){
            Intent intent = new Intent(MainActivity.this, CustomersActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.setMenu){
            Intent intent = new Intent(MainActivity.this, SetPrice.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.bill){
            Intent intent = new Intent(MainActivity.this, ShowBill.class);
            startActivity(intent);
        }
    }
}