package com.example.app_furniture_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.app_furniture_shop.Adapter.ViewPageAdapter;
import com.example.app_furniture_shop.Adapter.ViewPage_myOrderAdapter;
import com.example.app_furniture_shop.databinding.ActivityMyOrderBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MyOrderActivity extends AppCompatActivity {
    private ActivityMyOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMyOrderBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        setViewpage();
    }
    private void setViewpage(){
        binding.viewpageMyOrder.setAdapter(new ViewPage_myOrderAdapter(this));
        TabLayoutMediator tabLayoutMediator= new TabLayoutMediator(binding.tablayoutMyOrder, binding.viewpageMyOrder, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Delivered");
                        break;
                    case 1: tab.setText("Processing");
                        break;
                    case 2: tab.setText("Canceled");
                        break;

                }
            }
        });
        binding.tablayoutMyOrder.setTabGravity(TabLayout.GRAVITY_CENTER);
        binding.tablayoutMyOrder.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayoutMediator.attach();

    }
}