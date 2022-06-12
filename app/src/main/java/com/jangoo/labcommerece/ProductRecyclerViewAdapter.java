package com.jangoo.labcommerece;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder> {

    ArrayList<Product> products;
    private ItemClickListener clickListener;

    public ProductRecyclerViewAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.product_card_image.setImageResource(products.get(position).getImg());
        holder.product_card_text.setText(products.get(position).getTitle() + " - [" + products.get(position).getId() + "]");
        holder.product_card_price.setText("Price: Rs." + products.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView product_card_image;
        TextView product_card_text;
        TextView product_card_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            product_card_image = (ImageView) itemView.findViewById(R.id.product_card_image);
            product_card_text = (TextView) itemView.findViewById(R.id.product_card_title);
            product_card_price = (TextView) itemView.findViewById(R.id.product_card_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
