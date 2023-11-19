package com.example.app_furniture_shop.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.app_furniture_shop.Fragment.CanceledFragment;
import com.example.app_furniture_shop.Fragment.ChairFragment;
import com.example.app_furniture_shop.Fragment.DeliveredFragment;
import com.example.app_furniture_shop.Fragment.LampsFragment;
import com.example.app_furniture_shop.Fragment.MirrorFragment;
import com.example.app_furniture_shop.Fragment.PopularFragment;
import com.example.app_furniture_shop.Fragment.ProcessingFragment;
import com.example.app_furniture_shop.Fragment.SofaFragment;
import com.example.app_furniture_shop.Fragment.TableFragment;

public class ViewPage_myOrderAdapter extends FragmentStateAdapter {
    public ViewPage_myOrderAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new DeliveredFragment();
            case 1: return new ProcessingFragment();
            case 2: return new CanceledFragment();

            default: return new DeliveredFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
