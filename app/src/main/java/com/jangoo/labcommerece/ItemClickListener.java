package com.jangoo.labcommerece;

import android.view.View;

public interface ItemClickListener {
    void onClick(View view, int position);

    void onIncClick(View view, int adapterPosition);

    void onDecClick(View view, int adapterPosition);
}
