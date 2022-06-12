package com.jangoo.labcommerece;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ProductGalleryActivity extends AppCompatActivity implements ItemClickListener {

    String cur_id;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProductRecyclerViewAdapter recyclerViewAdapter;

    ArrayList<Product> polo_products = new ArrayList<>();
    ArrayList<Product> casual_products = new ArrayList<>();
    ArrayList<Product> bottom_products = new ArrayList<>();

    void init_data() {
        polo_products.add(new Product(
            "Polo Shirt RA2204-03 GLD",
            R.drawable.polo_1,
            "0",
            2950,
            0,
            "This pique cotton polo features a slash design with vibrant colors complimenting your active lifestyle. The Lycra pique fabric contains 94% Cotton and 6% Spandex, aiding seamless movement and comfort."
        ));
        casual_products.add(new Product(
                "Casual Shirt F/S PL S/F P22803-MR",
                R.drawable.casual_1,
                "0",
                3450,
                0,
                "A versatile and classic piece that is a must-have for your wardrobe. The slim-fit shirt goes well with jeans and chinos and can be tucked in or untucked for a more laid-back look."
        ));
        bottom_products.add(new Product(
                "Classic Fit Jeans JLZ1312-DBL",
                R.drawable.bottom_1,
                "0",
                3850,
                0,
                "The dark blue jeans are perfect for both outdoor and rugged looks. The durable blue jeans are crafted from 100% pure cotton fabric and stitched to perfection. The blue jeans pant is an exceptional product to don on outdoor adventures and regular usage. The top-notch quality of the product makes it worth your while."
        ));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_gallery);

        init_data();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        cur_id = id;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        if (id.equals("1")) {
            recyclerViewAdapter = new ProductRecyclerViewAdapter(polo_products);
        }
        if (id.equals("2")) {
            recyclerViewAdapter = new ProductRecyclerViewAdapter(casual_products);
        }
        if (id.equals("3")) {
            recyclerViewAdapter = new ProductRecyclerViewAdapter(bottom_products);
        }

        recyclerView.setAdapter(recyclerViewAdapter);
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
                Intent intent = new Intent(ProductGalleryActivity.this, CartActivity.class);
                ProductGalleryActivity.this.startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view, int position) {
        Product polo_product = polo_products.get(position);
        Product casual_product = casual_products.get(position);
        Product bottom_product = bottom_products.get(position);

        Intent intent = new Intent(ProductGalleryActivity.this, ProductDescriptionActivity.class);
        switch (cur_id) {
            case "1":
                intent.putExtra("title", polo_product.getTitle());
                intent.putExtra("id", polo_product.getId());
                intent.putExtra("quantity", polo_product.getQuantity());
                intent.putExtra("price", polo_product.getPrice());
                intent.putExtra("img", polo_product.getImg());
                intent.putExtra("description", polo_product.getDescription());
                break;
            case "2":
                intent.putExtra("title", casual_product.getTitle());
                intent.putExtra("id", casual_product.getId());
                intent.putExtra("quantity", casual_product.getQuantity());
                intent.putExtra("price", casual_product.getPrice());
                intent.putExtra("img", casual_product.getImg());
                intent.putExtra("description", casual_product.getDescription());
                break;
            case "3":
                intent.putExtra("title", bottom_product.getTitle());
                intent.putExtra("id", bottom_product.getId());
                intent.putExtra("quantity", bottom_product.getQuantity());
                intent.putExtra("price", bottom_product.getPrice());
                intent.putExtra("img", bottom_product.getImg());
                intent.putExtra("description", bottom_product.getDescription());
                break;
        }
        ProductGalleryActivity.this.startActivity(intent);
    }

    @Override
    public void onIncClick(View view, int adapterPosition) {}

    @Override
    public void onDecClick(View view, int adapterPosition) {}
}