package com.example.app_furniture_shop.Model;

public class Cart {
    private int id;
    private String name;
    private String image;
    private float price;
    private int quantity;

    public Cart() {
    }

    public Cart(int id, String name,float price, String image,  int quantity) {
        this.id = id;

        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
