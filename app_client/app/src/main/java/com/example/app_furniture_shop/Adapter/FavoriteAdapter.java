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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private ArrayList<Product> list;
    private Context context;
    private OnclickItem onclickItem;

    public FavoriteAdapter(ArrayList<Product> list, Context context, OnclickItem onclickItem) {
        this.list = list;
        this.context = context;
        this.onclickItem = onclickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_favorite,parent,false);
        return new FavoriteAdapter.ViewHolder(view);
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
        ImageView image,icon_close,icon_bag;
        TextView txtname,txtprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.img_favorite);
            icon_bag= itemView.findViewById(R.id.icon_bag_favorite);
            icon_close= itemView.findViewById(R.id.icon_close_favorite);
            txtname= itemView.findViewById(R.id.txtname_favorite);
            txtprice= itemView.findViewById(R.id.txtprice_favorite);

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
