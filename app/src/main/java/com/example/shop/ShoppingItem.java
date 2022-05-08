package com.example.shop;

public class ShoppingItem {
    private String id;
    private String name;
    private String info;
    private String price;
    private float ratedInfo;
    private int imageResource;
    private int cartedCount;

    public ShoppingItem( ) {

    }

    public ShoppingItem(String name, String info, String price, float ratedInfo, int imageResource, int cartedCount) {
        this.name = name;
        this.info = info;
        this.price = price;
        this.ratedInfo = ratedInfo;
        this.imageResource = imageResource;
        this.cartedCount = cartedCount;
    }

    public float getRatedInfo() {
        return ratedInfo;
    }
    public String getPrice() {
        return price;
    }
    public String getInfo() {
        return info;
    }
    public String getName() {
        return name;
    }
    public int getImageResource() {
        return imageResource;
    }
    public int getCartedCount() {return cartedCount;}

    public String _getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


