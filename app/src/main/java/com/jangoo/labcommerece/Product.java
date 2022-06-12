package com.jangoo.labcommerece;

public class Product {
    private String title;
    private int img;
    private String id;
    private int price;
    private int quantity;
    private String description;

    public Product(String title, int img, String id, int price, int quantity, String description) {
        this.title = title;
        this.img = img;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
