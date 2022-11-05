package com.example.restaurenthelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    private  Context context;
    private static MyDBHandler sInstance;

    private MyDBHandler(Context context) {

        super(context, Params.DB_NAME, null, Params.DB_VERSION);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String create1 = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_PRODUCT
                + " TEXT, " + Params.KEY_PRICE + " TEXT" + ")";
        String create2 = "CREATE TABLE " + Params.BILL_TABLE + "("
                + Params.TOTAL_BILL_ID + " INTEGER PRIMARY KEY," + Params.TABLE_ID
                + " TEXT, " + Params.TOTAL_PRODUCT + " TEXT,"+Params.PRICE_LIST+" TEXT,"+"totalPrice TEXT, date TEXT NOT NULL" + ")";

        try {
            db.execSQL(create1);
            db.execSQL(create2);
            Toast.makeText(context, "Noice", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Exception "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "Exception ", Toast.LENGTH_SHORT).show();
    }
    public static synchronized MyDBHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new MyDBHandler(context.getApplicationContext());
        }
        return sInstance;
    }
    public void addContact(String product, String price) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Params.KEY_PRODUCT, product);
            values.put(Params.KEY_PRICE, price);


            db.insert(Params.TABLE_NAME, null, values);
            Toast.makeText(context, "Insertion successful ", Toast.LENGTH_SHORT).show();
            db.close();
        }catch (Exception e){
            Toast.makeText(context, "Exception "+e, Toast.LENGTH_SHORT).show();
        }


    }
    public void addBill(String table_id, String totalProduct, String priceList, String totalPrice){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Params.TABLE_ID,table_id);
            values.put(Params.TOTAL_PRODUCT,totalProduct);
            values.put(Params.PRICE_LIST,priceList);
            values.put("totalPrice",totalPrice);
            String date;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                date = String.valueOf(java.time.LocalDate.now());
                values.put("date",date);
            }
            db.insert(Params.BILL_TABLE,null,values);
            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
        } catch (Exception e)
        {
            Toast.makeText(context, "Exception "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public List<Model> getAllContacts(){
        List<Model> modelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                Model model = new Model();
                //Toast.makeText(context, cursor.getString(1), Toast.LENGTH_SHORT).show();
                model.setPrice(cursor.getString(2));
                model.setProduct(cursor.getString(1));

                modelList.add(model);
            }while(cursor.moveToNext());
        }
        return modelList;
    }

    public List<BillModel> getAllBills(String date){
        List<BillModel> billList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.BILL_TABLE +" where date = ?";
        Cursor cursor = db.rawQuery(select, new String[]{date});

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                BillModel billModel = new BillModel();
                //Toast.makeText(context, cursor.getString(1), Toast.LENGTH_SHORT).show();
                billModel.setTableId(cursor.getString(1));
                billModel.setTotalProduct(cursor.getString(2));
                billModel.setPriceList(cursor.getString(3));
                billModel.setTotalPrice(cursor.getString(4));
                billModel.setDate(cursor.getString(5));

                billList.add(billModel);
            }while(cursor.moveToNext());
        }
        return billList;
    }

}