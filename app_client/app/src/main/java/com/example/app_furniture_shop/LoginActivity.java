package com.example.app_furniture_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.app_furniture_shop.DAO.UserDAO;
import com.example.app_furniture_shop.Database.RetrofitClient;
import com.example.app_furniture_shop.Fragment.PopularFragment;
import com.example.app_furniture_shop.Interface.APIManagerService;
import com.example.app_furniture_shop.Model.Product;
import com.example.app_furniture_shop.Model.ReqLogin;
import com.example.app_furniture_shop.Model.ReqMess;
import com.example.app_furniture_shop.databinding.ActivityLoginBinding;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);
        onClickLogin();
        binding.txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }
    private void onClickLogin(){
        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.edtemailLogin.getText().toString();
                String pass = binding.edtpassLogin.getText().toString();
                if (email.isEmpty() || pass.isEmpty()) {
                    binding.txterrLogin.setVisibility(View.VISIBLE);
                    binding.txterrLogin.setText("Fields cannot be left blank");
                } else if (!email.matches(EMAIL_REGEX)) {
                    binding.txterrLogin.setVisibility(View.VISIBLE);
                    binding.txterrLogin.setText("Email wrong format");
                } else {
                    binding.txterrLogin.setVisibility(View.GONE);
                    ResLogin resLogin=new ResLogin();
                    resLogin.email=email;
                    resLogin.password=pass;
                    APIManagerService apiManagerService= RetrofitClient.getService();
                    Call<ReqLogin> call=apiManagerService.Login(resLogin);
                    call.enqueue(new Callback<ReqLogin>() {
                        @Override
                        public void onResponse(Call<ReqLogin> call, Response<ReqLogin> response) {
                            if (response.isSuccessful()) {
                                ReqLogin loginResponse = response.body();
                                // Xử lý dữ liệu nhận được từ phản hồi
                                ReqLogin.Data data= loginResponse.getData();
                                UserDAO dao=new UserDAO(getApplicationContext());
                                if(dao.getUser().getId() >0){
                                    dao.deleteUser();
                                }
                                dao.addUser(data);
                                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                                // Hiển thị thông báo đăng nhập thành công
                            } else {
                                try {
                                    JSONObject errorBody = new JSONObject(response.errorBody().string());
                                    String errorMessage = errorBody.getString("message");
                                    System.out.println("1");
                                    System.out.println(errorMessage);
                                    binding.txterrLogin.setText(errorMessage);
                                    binding.txterrLogin.setVisibility(View.VISIBLE);
                                    // Xử lý khi có mã trạng thái không thành công
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }


                        @Override
                        public void onFailure(Call<ReqLogin> call, Throwable t) {

                        }
                    });

                }
            }
        });
    }
    public class ResLogin{
        String email;
        String password;


    }
}