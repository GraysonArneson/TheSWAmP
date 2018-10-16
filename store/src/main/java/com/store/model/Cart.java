package com.store.model;

import java.util.*;

public class Cart {

	private int cartId;
	private boolean purchased;
	private Collection<Product> items;


	public Cart(int cartId, Collection<Product> items, boolean purchased) {
		this.cartId = cartId;
		this.items = items;
		this.purchased = purchased;
	}

	public Cart(int cartId) {
		this.cartId = cartId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}

	public Collection<Product> getItems() {
		return items;
	}

	public void setItems(Collection<Product> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		String retString = "[\n{\n"
				+ "\"cartId\": \"" + cartId + "\""
				+ ", \n\"purchased\": \"" + purchased + "\""
				+ ", \n\"items\": [\n";
		String temp = "";
		int i =1;
		for(Product product: items) {
			if(i++==items.size())
				temp += product.toString();
			else
				temp += product.toString() + ",\n";
		}
		retString += temp;
		retString += "]\n}\n]";
		return retString;
	}
}
