package com.example.app_furniture_shop.Model;

public class ReqLogin {
    private int status;
    private Data data;
    private String message;

    // Các phương thức getter và setter cho các trường dữ liệu

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Định nghĩa lớp Data bên trong LoginResponse
    public static class Data {
        private int id;
        private String email;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
        // Các phương thức getter và setter cho các trường dữ liệu trong lớp Data
    }
}
