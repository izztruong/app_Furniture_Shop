package com.example.app_furniture_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_furniture_shop.Interface.APIManagerService;
import com.example.app_furniture_shop.Database.RetrofitClient;
import com.example.app_furniture_shop.Model.ReqMess;
import com.example.app_furniture_shop.databinding.ActivitySignupBinding;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
private ActivitySignupBinding binding;
    private String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignupBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        onClickSignup();
        binding.txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void onClickSignup(){
        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= binding.edtnameSignup.getText().toString();
                String email=binding.edtemailSignup.getText().toString();
                String pass=binding.edtpasswordSignup.getText().toString();
                String repass=binding.edtrepassSignup.getText().toString();

                if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                    binding.txterr.setVisibility(View.VISIBLE);
                    binding.txterr.setText("Fields cannot be left blank");
                } else if (!email.matches(EMAIL_REGEX)) {
                    binding.txterr.setVisibility(View.VISIBLE);
                    binding.txterr.setText("Email wrong format");
                } else if (pass.length() < 8 ) {
                    binding.txterr.setVisibility(View.VISIBLE);
                    binding.txterr.setText("Password atliest 8 charater");
                } else if (!pass.equals(repass)) {
                    binding.txterr.setVisibility(View.VISIBLE);
                    binding.txterr.setText("Password doesn't Repassword");
                } else {
                    binding.txterr.setVisibility(View.GONE);

                    ReqSignup reqSignup= new ReqSignup();
                    reqSignup.name=name;
                    reqSignup.email=email;
                    reqSignup.password=pass;

                    APIManagerService apiManagerService= RetrofitClient.getService();
                    Call<ReqMess> call=apiManagerService.register(reqSignup);
                    call.enqueue(new Callback<ReqMess>() {
                        @Override
                        public void onResponse(Call<ReqMess> call, Response<ReqMess> response) {
                              if (response.isSuccessful()) {
            ReqMess responseObject = response.body();
           String message = responseObject.getMessage();
           System.out.println("0");
           System.out.println(message);
           Intent inten=new Intent(SignupActivity.this,LoginActivity.class);
           startActivity(inten);

        } else {
            try {
                JSONObject errorBody = new JSONObject(response.errorBody().string());
                String errorMessage = errorBody.getString("message");
                System.out.println("1");
                System.out.println(errorMessage);
                binding.txterr.setText(errorMessage);
                // Xử lý khi có mã trạng thái không thành công
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
            }
        });
    }

    public class ReqSignup{
          String name;
          String email;
          String password;


    }
}