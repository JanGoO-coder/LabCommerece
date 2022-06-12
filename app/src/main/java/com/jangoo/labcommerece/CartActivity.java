package com.jangoo.labcommerece;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView.LayoutManager layoutManager;
    CartRecyclerViewAdapter recyclerViewAdapter;

    RecyclerView cart_recycler_view;

    Button cart_delete, cart_inc_btn, cart_dec_btn;
    TextView cart, cart_total;

    ArrayList<CartProduct> cartProducts = new ArrayList<>();

    Integer totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart_delete = (Button) findViewById(R.id.cart_delete);
        cart_inc_btn = (Button) findViewById(R.id.cart_inc_btn);
        cart_dec_btn = (Button) findViewById(R.id.cart_dec_btn);

        cart = (TextView) findViewById(R.id.cart);
        cart_total = (TextView) findViewById(R.id.cart_total);

        loadFromDatabase();

        for (int i = 0; i < cartProducts.size(); i++) {
            totalPrice += cartProducts.get(i).getQuantity() * cartProducts.get(i).getPrice();
        }

        cart.setText("Cart - [" + cartProducts.size() + "]");
        cart_total.setText("Total Price: Rs." + totalPrice);

        cart_recycler_view = (RecyclerView) findViewById(R.id.cart_recycler_view);
        layoutManager = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(cart_recycler_view.getContext(), LinearLayoutManager.VERTICAL);
        cart_recycler_view.addItemDecoration(dividerItemDecoration);
        cart_recycler_view.setLayoutManager(layoutManager);

        recyclerViewAdapter = new CartRecyclerViewAdapter(cartProducts);
        cart_recycler_view.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setClickListener(this);
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
                finish();
                Intent intent = new Intent(CartActivity.this, CartActivity.class);
                CartActivity.this.startActivity(intent);
                break;
        }
        return true;
    }

    @SuppressLint("Range")
    private void loadFromDatabase() {
        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.labcommerece.cart.provider/cart"), null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String t = cursor.getString(cursor.getColumnIndex("title"));
                Integer p = cursor.getInt(cursor.getColumnIndex("price"));
                Integer q = cursor.getInt(cursor.getColumnIndex("quantity"));

                cartProducts.add(new CartProduct(id, t, q, p));

                strBuild.append("\n" + id + " - " + t + " - "+ p + " - "+ q);
                cursor.moveToNext();
            }
            Toast.makeText(this, "" + strBuild, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view, int position) {
        String id = String.valueOf(cartProducts.get(position).getId());
        getContentResolver().delete(CartProvider.CONTENT_URI, "id=" + id, null);

        finish();
        Intent intent = getIntent();
        CartActivity.this.startActivity(intent);
    }

    @Override
    public void onIncClick(View view, int adapterPosition) {
        Integer qty = cartProducts.get(adapterPosition).getQuantity();
        cartProducts.get(adapterPosition).setQuantity(qty + 1);

        String id = String.valueOf(cartProducts.get(adapterPosition).getId());
        String t = cartProducts.get(adapterPosition).getTitle();
        Integer p = cartProducts.get(adapterPosition).getPrice();
        Integer q = cartProducts.get(adapterPosition).getQuantity();

        ContentValues values = new ContentValues();

        values.put(CartProvider.id, id);
        values.put(CartProvider.title, t);
        values.put(CartProvider.price, p);
        values.put(CartProvider.quantity, q);

        getContentResolver().update(CartProvider.CONTENT_URI, values, "id=" + id, null);

        finish();
        Intent intent = getIntent();
        CartActivity.this.startActivity(intent);
    }

    @Override
    public void onDecClick(View view, int adapterPosition) {
        Integer qty = cartProducts.get(adapterPosition).getQuantity();
        if (qty > 1) {
            if (qty > 1) {
                cartProducts.get(adapterPosition).setQuantity(qty - 1);
            }

            String id = String.valueOf(cartProducts.get(adapterPosition).getId());
            String t = cartProducts.get(adapterPosition).getTitle();
            Integer p = cartProducts.get(adapterPosition).getPrice();
            Integer q = cartProducts.get(adapterPosition).getQuantity();

            ContentValues values = new ContentValues();

            values.put(CartProvider.id, id);
            values.put(CartProvider.title, t);
            values.put(CartProvider.price, p);
            values.put(CartProvider.quantity, q);

            getContentResolver().update(CartProvider.CONTENT_URI, values, "id=" + id, null);

            finish();
            Intent intent = getIntent();
            CartActivity.this.startActivity(intent);
        }
    }
}