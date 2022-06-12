package com.jangoo.labcommerece;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView polos_banner, casuals_banner, bottoms_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        polos_banner = (ImageView) findViewById(R.id.polos_banner);
        polos_banner.setOnClickListener(this);

        casuals_banner = (ImageView) findViewById(R.id.casuals_banner);
        casuals_banner.setOnClickListener(this);

        bottoms_banner = (ImageView) findViewById(R.id.bottoms_banner);
        bottoms_banner.setOnClickListener(this);
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
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                MainActivity.this.startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, ProductGalleryActivity.class);
        if (view.getId() == polos_banner.getId()) {
            Toast.makeText(this, "Polos Gallery", Toast.LENGTH_SHORT).show();
            intent.putExtra("id", "1");
        }
        if (view.getId() == casuals_banner.getId()) {
            Toast.makeText(this, "Casualss Gallery", Toast.LENGTH_SHORT).show();
            intent.putExtra("id", "2");
        }
        if (view.getId() == bottoms_banner.getId()) {
            Toast.makeText(this, "Bottoms Gallery", Toast.LENGTH_SHORT).show();
            intent.putExtra("id", "3");
        }
        MainActivity.this.startActivity(intent);
    }
}

