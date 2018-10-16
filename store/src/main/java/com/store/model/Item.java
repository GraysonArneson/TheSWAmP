package com.store.model;

public class Item {
	
	private int itemId;
    private String name;
    private double msrp;
    private double salePrice;
    private long upc;
    private String shortDescription;
    private String brandName;
    private String size;
    private String color;
    private String gender;

    public Item () {

	}

//	public Item(int itemId, String name, double msrp, double salePrice) {
//		this.itemId = itemId;
//		this.name = name;
//		this.msrp = msrp;
//		this.salePrice = salePrice;
//	}


	public Item(int itemId, String name, double msrp, double salePrice, long upc, String shortDescription,
				String brandName, String size, String color, String gender) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.msrp = msrp;
		this.salePrice = salePrice;
		this.upc = upc;
		this.shortDescription = shortDescription;
		this.brandName = brandName;
		this.size = size;
		this.color = color;
		this.gender = gender;
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

	public long getUpc() {
		return upc;
	}

	public void setUpc(long upc) {
		this.upc = upc;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "{"
				+ "\"itemId\": \"" + itemId + "\""
				+ ", \"name\": \"" + name + "\""
				+ ", \"msrp\" :\"" + msrp + "\""
				+ ", \"salePrice\": \"" + salePrice + "\""
				+ ", \"upc\": \"" + upc + "\""
				+ ", \"shortDescription\": \"" + shortDescription + "\""
				+ ", \"brandName\": \"" + brandName + "\""
				+ ", \"size\": \"" + size + "\""
				+ ", \"color\": \"" + color + "\""
				+ ", \"gender\": \"" + gender + "\""
				+ "}";
	}
//
//
//	public String cartItemToString() {
//		return "{"
//				+ "\"productId\": \"" + itemId + "\""
//				+ ", \"productName\": \"" + name + "\""
//				+ ", \"msrp\" :\"" + msrp + "\""
//				+ ", \"salePrice\": \"" + salePrice + "\""
//				+ "}";
//	}
}
