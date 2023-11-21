package com.example.app_furniture_shop.Interface;

import com.example.app_furniture_shop.Fragment.PopularFragment;
import com.example.app_furniture_shop.LoginActivity;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.Model.ReqLogin;
import com.example.app_furniture_shop.Model.ReqMess;
import com.example.app_furniture_shop.ProductActivity;
import com.example.app_furniture_shop.SignupActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIManagerService {
    @GET("/api/product")
    Call<ArrayList<Product>> getListProduct();
    @GET("/api/productfollowcategory")
    Call<ArrayList<Product>> getProductfollowcategory(@Query("id") String id);
    @POST("/api/user/register")
    Call<ReqMess> register(@Body SignupActivity.ReqSignup user);
    @POST("/api/user/login")
    Call<ReqLogin> Login(@Body LoginActivity.ResLogin user);
    @POST("/api/cart/add")
    Call<ReqMess> addCart(@Body ProductActivity.ResCart cart);
    @POST("/api/cart/delete")
    Call<ReqMess> deleteCart(@Body ProductActivity.ResCart cart);
    @GET("/api/cart")
    Call<ArrayList<Product>> getCart(@Query("id")String id);


}
