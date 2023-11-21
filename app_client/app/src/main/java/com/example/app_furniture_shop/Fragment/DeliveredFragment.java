package com.example.app_furniture_shop.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_furniture_shop.Adapter.HomeAdapter;
import com.example.app_furniture_shop.Adapter.MyOrderAdapter;
import com.example.app_furniture_shop.Interface.OnclickItem;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.R;
import com.example.app_furniture_shop.databinding.FragmentDeliveredBinding;

import java.util.ArrayList;


public class DeliveredFragment extends Fragment {
    private FragmentDeliveredBinding binding;
    private ArrayList<Product> list;
    private MyOrderAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delivered, container, false);
        binding=FragmentDeliveredBinding.bind(view);
        setAdapter();
        return view;
    }
    private void setAdapter(){
        list= new ArrayList<>();
//        list.add(new Product(1,"aa",2,12,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
//        list.add(new Product(2,"bb",2,13,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167359/Books/mq53ueg7mf6685qszyha.webp","ddddddddddddddddddđ"));
//        list.add(new Product(3,"vv",2,14,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
//        list.add(new Product(4,"cc",2,15,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
//        list.add(new Product(5,"dd",2,12,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        adapter=new MyOrderAdapter(list,getContext());
        //System.out.println(list.get(1).getName());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());

        binding.rcvDelivered.setLayoutManager(layoutManager);
        binding.rcvDelivered.setAdapter(adapter);

    }
}