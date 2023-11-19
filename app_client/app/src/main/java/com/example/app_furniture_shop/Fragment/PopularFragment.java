package com.example.app_furniture_shop.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_furniture_shop.Adapter.HomeAdapter;
import com.example.app_furniture_shop.Interface.OnclickItem;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.ProductActivity;
import com.example.app_furniture_shop.R;
import com.example.app_furniture_shop.databinding.FragmentFavoriteBinding;
import com.example.app_furniture_shop.databinding.FragmentPopularBinding;

import java.io.Serializable;
import java.util.ArrayList;


public class PopularFragment extends Fragment implements OnclickItem {
    private FragmentPopularBinding binding;
    public HomeAdapter adapter;
    public ArrayList<Product> list;
    public Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_popular, container, false);
        binding= FragmentPopularBinding.bind(view);
        System.out.println("rr");
        setAdapter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapter();
    }

    private void setAdapter(){
        list= new ArrayList<>();
        list.add(new Product(1,"aa",2,12,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        list.add(new Product(2,"bb",2,13,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167359/Books/mq53ueg7mf6685qszyha.webp","ddddddddddddddddddđ"));
        list.add(new Product(3,"vv",2,14,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        list.add(new Product(4,"cc",2,15,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        list.add(new Product(5,"dd",2,12,"https://res.cloudinary.com/dkchoy5df/image/upload/v1691167512/Books/dgrmk3oikfxuni52gsmd.jpg","ddddddddddddddddddđ"));
        adapter=new HomeAdapter(list,getContext(), (OnclickItem) this);
        System.out.println(list.get(1).getName());
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2, RecyclerView.VERTICAL,false);

        binding.rcvPopular.setLayoutManager(layoutManager);
        binding.rcvPopular.setAdapter(adapter);

    }

    @Override
    public void OnclickItemSP(int po) {
        Product product= list.get(po);
        Intent intent= new Intent(getContext(),ProductActivity.class);
        intent.putExtra("chitietsanpham",  product);
        startActivity(intent);

    }

}