package com.example.app_furniture_shop.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.app_furniture_shop.Fragment.ChairFragment;
import com.example.app_furniture_shop.Fragment.LampsFragment;
import com.example.app_furniture_shop.Fragment.MirrorFragment;
import com.example.app_furniture_shop.Fragment.PopularFragment;
import com.example.app_furniture_shop.Fragment.SofaFragment;
import com.example.app_furniture_shop.Fragment.TableFragment;

public class ViewPageAdapter extends FragmentStateAdapter {
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new PopularFragment();
            case 1: return new ChairFragment();
            case 2: return new TableFragment();
            case 3: return new SofaFragment();
            case 4: return new LampsFragment();
            case 5: return new MirrorFragment();
            default: return new PopularFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
