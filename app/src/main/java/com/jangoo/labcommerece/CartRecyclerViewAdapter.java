package com.jangoo.labcommerece;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.MyCartViewHolder> {

    ArrayList<CartProduct> cartProducts;
    private ItemClickListener cartClickListener;

    public CartRecyclerViewAdapter(ArrayList<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    @NonNull
    @Override
    public CartRecyclerViewAdapter.MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row_layout, parent, false);
        return new MyCartViewHolder(view.getContext(), view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewAdapter.MyCartViewHolder holder, int position) {
        holder.cart_title.setText(cartProducts.get(position).getTitle());
        holder.cart_id.setText("ID: " + cartProducts.get(position).getId());
        holder.cart_price.setText("Price: " + cartProducts.get(position).getPrice());
        holder.cart_quantity.setText("" + cartProducts.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.cartClickListener = itemClickListener;
    }

    public class MyCartViewHolder extends RecyclerView.ViewHolder {

        TextView cart_title;
        TextView cart_id;
        TextView cart_price;
        TextView cart_quantity;

        private Context context;

        public MyCartViewHolder(Context context, @NonNull View itemView) {
            super(itemView);

            cart_title = (TextView) itemView.findViewById(R.id.cart_title);
            cart_id = (TextView) itemView.findViewById(R.id.cart_id);
            cart_price = (TextView) itemView.findViewById(R.id.cart_price);
            cart_quantity = (TextView) itemView.findViewById(R.id.cart_quantity);

            this.context = context;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = String.valueOf(cartProducts.get(getAdapterPosition()).getId());
//                    Toast.makeText(view.getContext(), "id:" + id, Toast.LENGTH_SHORT).show();
                }
            });

            itemView.findViewById(R.id.cart_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cartClickListener != null) cartClickListener.onClick(view, getAdapterPosition());
                }
            });

            itemView.findViewById(R.id.cart_inc_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cartClickListener != null) cartClickListener.onIncClick(view, getAdapterPosition());
                }
            });

            itemView.findViewById(R.id.cart_dec_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cartClickListener != null) cartClickListener.onDecClick(view, getAdapterPosition());
                }
            });
        }
    }
}
