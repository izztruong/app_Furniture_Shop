package com.example.app_furniture_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_furniture_shop.databinding.ActivityManHinhChaoBinding;

public class Man_hinh_chaoActivity extends AppCompatActivity {
   private ActivityManHinhChaoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManHinhChaoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Man_hinh_chaoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }



}