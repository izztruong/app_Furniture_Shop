package com.example.app_furniture_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_furniture_shop.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);
        binding.txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}