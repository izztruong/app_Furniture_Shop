package com.example.app_furniture_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.databinding.ActivityProductBinding;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {
   private ActivityProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);
        initData();
        ActionToolBar();
    }
    private void initData(){
        Product product= (Product) getIntent().getSerializableExtra("chitietsanpham");
        binding.txtnameSp.setText(product.getName());

        binding.txtpriceSp.setText(String.valueOf(product.getPrice()));
        binding.txtdescriptionSp.setText(product.getDescription());
        Picasso.get().load(product.getImage()).into(binding.imgSp);
    }
    private void ActionToolBar(){
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}