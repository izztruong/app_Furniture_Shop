package com.example.app_furniture_shop.Model;

import com.google.gson.annotations.SerializedName;

public class ReqMess {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }


}
