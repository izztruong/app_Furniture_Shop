package com.example.app_furniture_shop.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app_furniture_shop.Adapter.FavoriteAdapter;
import com.example.app_furniture_shop.Adapter.HomeAdapter;
import com.example.app_furniture_shop.CartActivity;
import com.example.app_furniture_shop.Interface.OnclickItem;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.ProductActivity;
import com.example.app_furniture_shop.R;
import com.example.app_furniture_shop.databinding.FragmentFavoriteBinding;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment implements OnclickItem {
    private FragmentFavoriteBinding binding;
    private ArrayList<Product> list;
    private FavoriteAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        binding= FragmentFavoriteBinding.bind(view);
        setAdapter();
        clickIconCart();
        return view;
    }
    private void setAdapter(){
        list= new ArrayList<>();
//        list.add(new Product(1,"aa",2,12,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
//        list.add(new Product(2,"bb",2,13,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167359/Books/mq53ueg7mf6685qszyha.webp","ddddddddddddddddddđ"));
//        list.add(new Product(3,"vv",2,14,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
//        list.add(new Product(4,"cc",2,15,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
//        list.add(new Product(5,"dd",2,12,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        adapter=new FavoriteAdapter(list,getContext(), (OnclickItem) this);
       // System.out.println(list.get(1).getName());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.rcvFav.setLayoutManager(layoutManager);
        binding.rcvFav.setAdapter(adapter);

    }

    @Override
    public void OnclickItemSP(int po) {
        Product product= list.get(po);
        Intent intent= new Intent(getContext(), ProductActivity.class);
        intent.putExtra("chitietsanpham",  product);
        startActivity(intent);

    }
    private void clickIconCart(){
        binding.iconcartFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });

    }



}