package com.example.app_furniture_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_furniture_shop.Adapter.CartAdapter;
import com.example.app_furniture_shop.Adapter.FavoriteAdapter;
import com.example.app_furniture_shop.Interface.OnclickItem;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private ArrayList<Product> list;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);
        ActionToolBar();
        clickButton();
        setAdapter();

    }
    private void setAdapter(){
        list= new ArrayList<>();
        list.add(new Product(1,"aa",2,12,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        list.add(new Product(2,"bb",2,13,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167359/Books/mq53ueg7mf6685qszyha.webp","ddddddddddddddddddđ"));
        list.add(new Product(3,"vv",2,14,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        list.add(new Product(4,"cc",2,15,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        list.add(new Product(5,"dd",2,12,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        adapter=new CartAdapter(list,this);
        System.out.println(list.get(1).getName());
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
}