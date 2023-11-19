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

import com.example.app_furniture_shop.Interface.OnclickItem;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private ArrayList<Product> list;
    private Context context;
    OnclickItem onclickItem;

    public HomeAdapter(ArrayList<Product> list, Context context,OnclickItem onclickItem) {
        this.list = list;
        this.context = context;
        this.onclickItem=onclickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_sp_home,parent,false);
        return new ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname;
        TextView txtprice;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname= itemView.findViewById(R.id.txtname_item_sp_home);
            txtprice=itemView.findViewById(R.id.txtprice_item_sp_home);
            image=itemView.findViewById(R.id.image_item_sp_home);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onclickItem!=null){
                        int po=getAdapterPosition();
                        if(po!=RecyclerView.NO_POSITION){
                            onclickItem.OnclickItemSP(po);
                        }
                    }
                }
            });
        }
    }
    }
