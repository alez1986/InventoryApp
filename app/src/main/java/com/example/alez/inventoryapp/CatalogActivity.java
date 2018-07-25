package com.example.alez.inventoryapp;


import android.content.Intent;
import android.database.Cursor;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.alez.inventoryapp.data.InventoryContract.InventoryEntry;


public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        ListView productListView = (ListView) findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        productListView.setEmptyView(emptyView);
    }


    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }


    private void displayDatabaseInfo() {
        Cursor cursor = queryData();

        // Find the ListView which will be populated with the product data
        ListView productListView = (ListView) findViewById(R.id.list);

        // Setup an Adapter to create a list item for each row of product data in the Cursor.
        ProductCursorAdapter adapter = new ProductCursorAdapter(this, cursor);

        // Attach the adapter to the ListView.
        productListView.setAdapter(adapter);
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
