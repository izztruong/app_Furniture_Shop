package com.example.app_furniture_shop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_furniture_shop.Adapter.ViewPageAdapter;
import com.example.app_furniture_shop.CartActivity;
import com.example.app_furniture_shop.R;
import com.example.app_furniture_shop.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        binding=FragmentHomeBinding.bind(view);
        setHasOptionsMenu(true);
        setViewpage();
        clickIconCart();
        return view;

    }
    private void setViewpage(){
        binding.viewpagedanhmuc.setAdapter(new ViewPageAdapter(getActivity()));
        TabLayoutMediator tabLayoutMediator= new TabLayoutMediator(binding.tabdanhmuc, binding.viewpagedanhmuc, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Popular");
                    tab.setIcon(R.drawable.ic_baseline_star_border_24);
                    break;
                    case 1: tab.setText("Chair");
                        tab.setIcon(R.drawable.ic_baseline_chair_alt_24);
                        break;
                    case 2: tab.setText("Table");
                        tab.setIcon(R.drawable.ic_baseline_table_restaurant_24);
                        break;
                    case 3: tab.setText("Sofa");
                        tab.setIcon(R.drawable.ic_baseline_chair_24);
                        break;
                    case 4: tab.setText("Lumps");
                        tab.setIcon(R.drawable.ic_baseline_light_24);
                        break;
                    case 5: tab.setText("Mirror");
                        tab.setIcon(R.drawable.ic_baseline_crop_portrait_24);
                        break;
                }
            }
        });
        binding.tabdanhmuc.setTabGravity(TabLayout.GRAVITY_CENTER);
        binding.tabdanhmuc.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayoutMediator.attach();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void clickIconCart(){
        binding.iconcartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });

    }
}