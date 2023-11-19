package com.example.app_furniture_shop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_furniture_shop.MyOrderActivity;
import com.example.app_furniture_shop.R;
import com.example.app_furniture_shop.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        binding=FragmentProfileBinding.bind(view);
        onClickIconMyOrder();
        return view;
    }
    private void onClickIconMyOrder(){
        binding.iconmyorderProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}