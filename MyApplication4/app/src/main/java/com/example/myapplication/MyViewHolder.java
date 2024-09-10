package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView itemText;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemText = itemView.findViewById(R.id.item_text);
    }
}
