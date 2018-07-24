package com.example.alez.inventoryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.alez.inventoryapp.data.InventoryContract.InventoryEntry;
import com.example.alez.inventoryapp.data.InventoryDbHelper;


public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        //insertData();
        //queryData();
    }


    @Override
    protected void onStart() {
        super.onStart();
        //TODO: We will use this method to display all items from DB
    }


    //* Add new item to DB */
    private void insertData(){
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, "Default item");
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, 100);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, 1);
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "Default supplier");
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, "+00000000000");

        Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI, values);

        Log.v("InsertData", "Row inserted with uri "+ newUri);
    }


    //* Return data from DB */
    private Cursor queryData(){
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRODUCT_PRICE,
                InventoryEntry.COLUMN_PRODUCT_QUANTITY,
                InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER };

        // Perform a query on the product_inventory table
        return getContentResolver().query(InventoryEntry.CONTENT_URI, projection, null, null, null);
    }
}
