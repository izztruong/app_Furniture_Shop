package com.example.app_furniture_shop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_furniture_shop.Adapter.HomeAdapter;
import com.example.app_furniture_shop.Database.RetrofitClient;
import com.example.app_furniture_shop.Interface.APIManagerService;
import com.example.app_furniture_shop.Interface.OnclickItem;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.ProductActivity;
import com.example.app_furniture_shop.R;
import com.example.app_furniture_shop.databinding.FragmentLampsBinding;
import com.example.app_furniture_shop.databinding.FragmentMirrorBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MirrorFragment extends Fragment implements OnclickItem {
    private FragmentMirrorBinding binding;
    public HomeAdapter adapter;
    public ArrayList<Product> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mirror, container, false);
        binding= FragmentMirrorBinding.bind(view);
        setView();
        return view;
    }
    private void setView(){
        APIManagerService apiManagerService= RetrofitClient.getService();
        Call<ArrayList<Product>> call=apiManagerService.getProductfollowcategory("5");
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if(response.isSuccessful()){
                    list.clear();
                    list.addAll(response.body());
                    setAdapter(list);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    private void setAdapter(ArrayList<Product> list){
        adapter=new HomeAdapter(list,getContext(),  this);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2, RecyclerView.VERTICAL,false);
        binding.rcvMirror.setLayoutManager(layoutManager);
        binding.rcvMirror.setAdapter(adapter);

    }
    @Override
    public void OnclickItemSP(int po) {
        Product product= list.get(po);
        Intent intent= new Intent(getContext(), ProductActivity.class);
        intent.putExtra("chitietsanpham",  product);
        startActivity(intent);

    }
}