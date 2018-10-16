package com.store.model;

public class Product {

    private int itemId;
    private String name;
    private double msrp;
    private double salePrice;

    public Product(int itemId, String name, double msrp, double salePrice) {
        this.itemId = itemId;
        this.name = name;
        this.msrp = msrp;
        this.salePrice = salePrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "\t{\n\t"
                + "\"itemId\": \"" + itemId + "\""
                + ", \n\t\t\"name\": \"" + name + "\""
                + ", \n\t\t\"msrp\": \"" + msrp + "\""
                + ", \n\t\t\"salePrice\": \"" + salePrice + "\"\n"
                + "\t\t}";
    }
}
