package com.example.app_furniture_shop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    private ArrayList<Product> list;
    private Context context;

    public MyOrderAdapter(ArrayList<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_myorder,parent,false);
        return new MyOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtname.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getImage()).into(holder.image);
        holder.txtprice.setText(String.valueOf(list.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView txtname,txtprice,txtquantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.img_item_myorder);
            txtname=itemView.findViewById(R.id.txtname_myorder);
            txtprice=itemView.findViewById(R.id.txtprice_myorder);
            txtquantity=itemView.findViewById(R.id.txtprice_myorder);
        }
    }
}
