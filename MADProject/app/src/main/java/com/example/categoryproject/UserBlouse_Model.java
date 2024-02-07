package com.example.categoryproject;

public class UserBlouse_Model {


    String name,price,iurl,size;

    UserBlouse_Model(){

    }

    public UserBlouse_Model(String name, String price, String iurl, String size) {
        this.name = name;
        this.price = price;
        this.iurl = iurl;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIurl() {
        return iurl;
    }

    public void setIurl(String iurl) {
        this.iurl = iurl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
