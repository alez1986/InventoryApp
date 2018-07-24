package com.example.alez.inventoryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        //insertData();
        //queryData();
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

        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, "Default item");
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, 100);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, 1);
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "Default supplier");
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, "+00000000000");

        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);
        Log.v("InsertData", "Row id inserted "+newRowId);
    }


    //* Return data from DB */
    private void queryData(){
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

        if (cursor.moveToFirst()) {
            do {
                Log.v("Query", "Product: ");
                Log.v("Query", "name: " + cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME)));
                Log.v("Query", "price: " + cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE)));
                Log.v("Query", "quantity: " + cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY)));
                Log.v("Query", "supplier: " + cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME)));
                Log.v("Query", "supplier phone: " + cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER)));
            } while (cursor.moveToNext());
        }

        cursor.close();
    }
}
