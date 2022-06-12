package com.jangoo.labcommerece;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    String title;
    String id;
    String description;
    int price;
    int quantity;
    int img;

    ImageView product_img;
    TextView product_title;
    TextView product_price;
    TextView product_quantity;
    TextView getProduct_description;

    Button increase_btn, decrease_btn;

    Button add_to_cart_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        id = intent.getStringExtra("id");
        description = intent.getStringExtra("description");
        price = intent.getIntExtra("price", 0);
        quantity = intent.getIntExtra("quantity", 0);
        img = intent.getIntExtra("img", 0);

        product_img = (ImageView) findViewById(R.id.product_img);
        product_title = (TextView) findViewById(R.id.product_title);
        product_price = (TextView) findViewById(R.id.product_price);
        product_quantity = (TextView) findViewById(R.id.product_quantity);
        getProduct_description = (TextView) findViewById(R.id.product_description);

        increase_btn = (Button) findViewById(R.id.inc_btn);
        increase_btn.setOnClickListener(ProductDescriptionActivity.this);
        decrease_btn = (Button) findViewById(R.id.dec_btn);
        decrease_btn.setOnClickListener(ProductDescriptionActivity.this);

        add_to_cart_button = (Button) findViewById(R.id.add_to_cart_btn);
        add_to_cart_button.setOnClickListener(ProductDescriptionActivity.this);

        product_img.setImageResource(img);
        product_title.setText(title);
        product_price.setText("Price: " + price);
        product_quantity.setText("" + quantity);
        getProduct_description.setText(description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_btn:
                Intent intent = new Intent(ProductDescriptionActivity.this, CartActivity.class);
                ProductDescriptionActivity.this.startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
        Toast.makeText(this, "Changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == increase_btn.getId()) {
            quantity = quantity + 1;
            product_quantity.setText("" + quantity);
        }
        if (view.getId() == decrease_btn.getId()) {
            if (quantity > 0) { quantity = quantity - 1; }
            product_quantity.setText("" + quantity);
        }
        if (view.getId() == add_to_cart_button.getId() && quantity > 0) {
            addItemToCartDatabase(title, price, quantity);

            Intent intent = new Intent(ProductDescriptionActivity.this, CartActivity.class);
            ProductDescriptionActivity.this.startActivity(intent);
        } else {
            Toast.makeText(this, "Quantity Must be atleast 1", Toast.LENGTH_SHORT).show();
        }
    }

    private void addItemToCartDatabase(String t, Integer p, Integer q) {
        ContentValues values = new ContentValues();

        values.put(CartProvider.title, t);
        values.put(CartProvider.price, p);
        values.put(CartProvider.quantity, q);

        getContentResolver().insert(CartProvider.CONTENT_URI, values);

        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.labcommerece.cart.provider/cart"), null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("title"))
                    + "-"+ cursor.getString(cursor.getColumnIndex("price"))
                    + "-"+ cursor.getString(cursor.getColumnIndex("quantity")));
                cursor.moveToNext();
            }
            Toast.makeText(this, "Record Added Successfully!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
        }
    }
}