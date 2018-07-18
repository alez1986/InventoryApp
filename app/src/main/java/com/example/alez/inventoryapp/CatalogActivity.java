package com.example.alez.inventoryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alez.inventoryapp.data.InventoryContract.InventoryEntry;
import com.example.alez.inventoryapp.data.InventoryDbHelper;


public class CatalogActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // To access database
        mDbHelper = new InventoryDbHelper(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //TODO: We will use this methd to display all items from DB
    }


    //* Add new item to DB */
    private void insertData(){
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, "Default item");
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, 100);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, 1);
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "Default supplier");
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, "+00000000000");

        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);
    }


    //* Return data from DB */
    private Cursor queryData(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRODUCT_PRICE,
                InventoryEntry.COLUMN_PRODUCT_QUANTITY,
                InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER };

        // Perform a query on the product_inventory table
        Cursor cursor = db.query(
                InventoryEntry.TABLE_NAME,          // The table to query
                projection,                         // The columns to return
                null,                       // The columns for the WHERE clause
                null,                   // The values for the WHERE clause
                null,                       // Don't group the rows
                null,                       // Don't filter by row groups
                null);                      // The sort order

        return cursor;
    }
}
