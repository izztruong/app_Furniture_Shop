package com.example.app_furniture_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.app_furniture_shop.Fragment.FavoriteFragment;
import com.example.app_furniture_shop.Fragment.HomeFragment;
import com.example.app_furniture_shop.Fragment.NotificateFragment;
import com.example.app_furniture_shop.Fragment.ProfileFragment;
import com.example.app_furniture_shop.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);
        clicknavbottom();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameHome, new HomeFragment()).commit();

    }

    private void clicknavbottom() {

        binding.navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.icon_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.icon_yeuthich:
                        fragment = new FavoriteFragment();
                        break;
                    case R.id.icon_thongbao:
                            fragment = new NotificateFragment();
                        break;
                    case R.id.icon_user:

                            fragment = new ProfileFragment();

                        break;
                    default:
                        fragment = new HomeFragment();
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameHome, fragment).commit();
                }
                return true;
            }
        });
    }
}