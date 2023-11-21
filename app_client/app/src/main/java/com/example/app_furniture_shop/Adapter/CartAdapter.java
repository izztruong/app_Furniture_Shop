package com.example.app_furniture_shop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_furniture_shop.DAO.CartDAO;
import com.example.app_furniture_shop.DAO.UserDAO;
import com.example.app_furniture_shop.Database.RetrofitClient;
import com.example.app_furniture_shop.Interface.APIManagerService;
import com.example.app_furniture_shop.Interface.OnclickIcon;
import com.example.app_furniture_shop.Model.Cart;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.Model.ReqMess;
import com.example.app_furniture_shop.ProductActivity;
import com.example.app_furniture_shop.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Product> list;
    private Context context;
    OnclickIcon onclickIcon;

    public CartAdapter(ArrayList<Product> list, Context context,OnclickIcon onclickIcon) {
        this.list = list;
        this.context = context;
        this.onclickIcon=onclickIcon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_mycart,parent,false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtname.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getImage()).into(holder.image);
        holder.txtprice.setText(String.valueOf(list.get(position).getPrice()));
        holder.icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDAO dao=new UserDAO(context);
                ProductActivity.ResCart cart=new ProductActivity.ResCart();
                cart.setIduser(String.valueOf(dao.getUser().getId()));
                cart.setIdproduct(String.valueOf(list.get(position).getId()));

                APIManagerService apiManagerService= RetrofitClient.getService();
                Call<ReqMess> call=apiManagerService.deleteCart(cart);
                call.enqueue(new Callback<ReqMess>() {
                    @Override
                    public void onResponse(Call<ReqMess> call, Response<ReqMess> response) {
                        if (response.isSuccessful()) {
                            ReqMess responseObject = response.body();
                            String message = responseObject.getMessage();
                            System.out.println("0");
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            onclickIcon.OnclickIconClose(position);
                        }else {
                            try {
                                JSONObject errorBody = new JSONObject(response.errorBody().string());
                                String errorMessage = errorBody.getString("message");
                                System.out.println("1");
                                System.out.println(errorMessage);
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ReqMess> call, Throwable t) {
                          System.out.println(t.getMessage());
                    }
                });
            }
        });
        holder.icon_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong=Integer.parseInt((String) holder.txtquantity.getText())+1;
                CartDAO dao=new CartDAO(context);
                dao.updateSoLuong(list.get(position).getId(),soluong);
                holder.txtquantity.setText(String.valueOf(soluong));
                onclickIcon.OnclickIconAdd(position);
            }
        });
        holder.icon_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong=Integer.parseInt((String) holder.txtquantity.getText())-1;
                if (soluong<1){
                    soluong=1;
                }
                CartDAO dao=new CartDAO(context);
                dao.updateSoLuong(list.get(position).getId(),soluong);
                holder.txtquantity.setText(String.valueOf(soluong));
                onclickIcon.OnclickIconReduce(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image,icon_add,icon_reduce,icon_close;
        TextView txtname,txtprice,txtquantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.img_item_mycart);
            icon_add=itemView.findViewById(R.id.iconadd_mycart);
            icon_reduce=itemView.findViewById(R.id.iconreduce_mycart);
            icon_close=itemView.findViewById(R.id.icon_close_mycart);
            txtname=itemView.findViewById(R.id.txtname_mycart);
            txtprice=itemView.findViewById(R.id.txtprice_mycart);
            txtquantity=itemView.findViewById(R.id.txtquantity_mycart);

        }
    }
}
