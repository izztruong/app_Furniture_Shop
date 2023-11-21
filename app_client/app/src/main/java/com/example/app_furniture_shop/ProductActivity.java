package com.example.app_furniture_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.app_furniture_shop.DAO.UserDAO;
import com.example.app_furniture_shop.Database.RetrofitClient;
import com.example.app_furniture_shop.Interface.APIManagerService;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.Model.ReqMess;
import com.example.app_furniture_shop.databinding.ActivityProductBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
   private ActivityProductBinding binding;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        product= (Product) getIntent().getSerializableExtra("chitietsanpham");
        setContentView(view);
        initData();
        ActionToolBar();
        onClickAdd();
    }
    private void initData(){
        product= (Product) getIntent().getSerializableExtra("chitietsanpham");
        binding.txtnameSp.setText(product.getName());
        binding.txtpriceSp.setText(String.valueOf(product.getPrice()));
        binding.txtdescriptionSp.setText(product.getDescription());
        Picasso.get().load(product.getImage()).into(binding.imgSp);
    }
    private void ActionToolBar(){
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void onClickAdd(){
        binding.btnaddtocartSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDAO dao=new UserDAO(getApplicationContext());
                ResCart resCart=new ResCart();
                resCart.idproduct= String.valueOf(product.getId());
                resCart.iduser= String.valueOf(dao.getUser().getId());
                APIManagerService apiManagerService= RetrofitClient.getService();
                Call<ReqMess> call=apiManagerService.addCart(resCart);
                call.enqueue(new Callback<ReqMess>() {
                    @Override
                    public void onResponse(Call<ReqMess> call, Response<ReqMess> response) {
                        if (response.isSuccessful()) {
                            ReqMess responseObject = response.body();
                            String message = responseObject.getMessage();
                            System.out.println("0");
                            Toast.makeText(ProductActivity.this, message, Toast.LENGTH_SHORT).show();
                    }else {
                            try {
                                JSONObject errorBody = new JSONObject(response.errorBody().string());
                                String errorMessage = errorBody.getString("message");
                                System.out.println("1");
                                System.out.println(errorMessage);
                                Toast.makeText(ProductActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ReqMess> call, Throwable t) {

                    }
                });
            }
        });
    }
    public static class ResCart{
        String idproduct;
        String iduser;

        public String getIdproduct() {
            return idproduct;
        }

        public void setIdproduct(String idproduct) {
            this.idproduct = idproduct;
        }

        public String getIduser() {
            return iduser;
        }

        public void setIduser(String iduser) {
            this.iduser = iduser;
        }
    }
}