package com.store.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

public class CartDAO {

    private JdbcTemplate jdbcTemplate;

    public CartDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    private ItemDAO itemDAO = new ItemDAO(jdbcTemplate);

    public void createCart(String username){
        //TODO: Implement this CRUD function
        this.jdbcTemplate.update("INSERT INTO carts (username, purchased ) VALUES (?, ?)",  username, false);
    }

    public void addToCart(int cartId, int productId) {
        this.jdbcTemplate.update("INSERT INTO cartItems (cartId, productId) VALUES (?, ?)", cartId, productId);
    }


    public Cart getCartByUsername(String username) {
        String query = "SELECT * FROM carts WHERE username = ? AND purchased = false ";
        Cart cart;
        try {
            cart = this.jdbcTemplate.queryForObject(query, new Object[]{username},
                    (rs, rowNum) -> new Cart(rs.getInt("cartId")));
        }
        catch (Exception e) {
            cart = null;
        }
        return cart;
    }

    public void addItem(String username, int productId) {
        Cart cart = getCartByUsername(username);
        if(cart != null) {
            addToCart(cart.getCartId(),productId);
        }
        else {
            createCart(username);
            addItem(username, productId);
        }
    }


    public Collection<Product> getShoppingCartInformation(int cartId) {
        String query = "SELECT cartItems.productId AS productId, products.name AS productName, products.msrp AS msrp, products.salePrice as salePrice " +
                "FROM cartItems LEFT JOIN products ON cartItems.productId = products.itemId WHERE cartItems.cartId = ?";
        Collection<Product> items = new ArrayList<Product>();
        this.jdbcTemplate.query(query, new Object[] { cartId },
                (rs, rowNum) -> new Product(rs.getInt("productId"), rs.getString("productName")
                        , rs.getDouble("msrp"), rs.getDouble("salePrice")))
                .forEach(item -> items.add(item));
        return items;
    }

    public Collection<Cart> getCartsByUsername(String username) {
        Collection<Cart> carts = new ArrayList<Cart>();
        String query = "SELECT * FROM carts WHERE username = ?";
        this.jdbcTemplate.query(query, new Object[]{ username },
                (rs, rowNum) -> new Cart(rs.getInt("cartId"),
                        getShoppingCartInformation(rs.getInt("cartId")), rs.getBoolean("purchased")))
                        .forEach(cart -> carts.add(cart));
        return carts;

    }

    public Collection<Cart> showCart(String username) {
        Collection<Cart> carts = getCartsByUsername(username);
        return carts;
    }

    public boolean removeFromCart(int cartId, int productId) {
        String query = "DELETE FROM cartItems WHERE cartId = ? and productId = ?";
        int x = this.jdbcTemplate.update(query, cartId, productId);
        if(x==1)
            return true;
        else
            return false;

    }

    public void purchaseCart(int cartId) {
        String query = "UPDATE carts SET purchased = ? WHERE cartId = ?";
        this.jdbcTemplate.update(query, true, cartId);
    }

    public Collection<String> getProductPurchaseUsers(int productId) {
        Collection<Cart> carts;
        Collection<String> users = new ArrayList<>();
        String query = "SELECT carts.username AS username FROM carts INNER JOIN cartItems ON cartItems.cartId = carts.cartId WHERE productId = ?";
        Collection<String> temp = new ArrayList<>();
        this.jdbcTemplate.query(query, new Object[]{productId},
                (rs, rowNum) -> rs.getString("username"))
                .forEach(s -> users.add(s));

        return users;
    }
}