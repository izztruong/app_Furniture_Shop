package com.example.app_furniture_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_furniture_shop.Adapter.CartAdapter;
import com.example.app_furniture_shop.Adapter.FavoriteAdapter;
import com.example.app_furniture_shop.DAO.CartDAO;
import com.example.app_furniture_shop.DAO.UserDAO;
import com.example.app_furniture_shop.Database.RetrofitClient;
import com.example.app_furniture_shop.Interface.APIManagerService;
import com.example.app_furniture_shop.Interface.OnclickIcon;
import com.example.app_furniture_shop.Interface.OnclickItem;
import com.example.app_furniture_shop.Model.Cart;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.databinding.ActivityCartBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements OnclickIcon {
    private ActivityCartBinding binding;
    private ArrayList<Product> list=new ArrayList<>();
    private CartAdapter adapter=new CartAdapter(list,this,  this);
    CartDAO cartDAO=new CartDAO(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);
        ActionToolBar();
        clickButton();
        setData();


    }
    private void setData(){
        UserDAO userDAO=new UserDAO(getApplicationContext());
        APIManagerService apiManagerService= RetrofitClient.getService();
        Call<ArrayList<Product>> call=apiManagerService.getCart(String.valueOf(userDAO.getUser().getId()));
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if(response.isSuccessful()){
                    list.clear();
                    list.addAll(response.body());
                    cartDAO.delete();
                    for(Product x:list){
                        Cart cart=new Cart(x.getId(),x.getName(),x.getPrice(),x.getImage(),1);
                        cartDAO.addCart(cart);
                    }
                    setAdapter(list);
                    totalAmount();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                  System.out.println(t.getMessage());
            }
        });
    }
    private void setAdapter(ArrayList<Product> list){
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.rcvCart.setLayoutManager(layoutManager);
        binding.rcvCart.setAdapter(adapter);

    }
    private void ActionToolBar(){
        setSupportActionBar(binding.toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        binding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void clickButton(){
        binding.btncheckoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CartActivity.this,CheckOutActivity.class);
                startActivity(intent);
            }
        });
    }
    private void totalAmount(){
        float total=0;
        ArrayList<Cart> listsp;
        listsp=cartDAO.getAll();
        for (Cart x:listsp){
            total+=(x.getPrice()*x.getQuantity());
        }
        binding.txttotalCart.setText(String.valueOf(total));
    }

    @Override
    public void OnclickIconClose(int po) {
        list.remove(po);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnclickIconAdd(int po) {
        totalAmount();
        adapter.notifyDataSetChanged();

    }

    @Override
    public void OnclickIconReduce(int po) {
        totalAmount();
        adapter.notifyDataSetChanged();
    }
}