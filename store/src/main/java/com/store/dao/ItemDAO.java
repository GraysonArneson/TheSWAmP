package com.store.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;


public class ItemDAO {
    private JdbcTemplate jdbcTemplate;

    public ItemDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }


    public Collection<Item> getAllItems(){
        Collection<Item> items = new ArrayList<Item>();
        String query = "SELECT * FROM products";
        this.jdbcTemplate.query(
                query, new Object[] {},
                (rs, rowNum) -> new Item(rs.getInt("itemId"), rs.getString("name"),
                        rs.getDouble("msrp"), rs.getDouble("salePrice"), rs.getLong("upc")
                        , rs.getString("shortDescription"), rs.getString("brandName")
                        , rs.getString("size"), rs.getString("color"), rs.getString("gender"))
        ).forEach(item -> ((ArrayList<Item>) items).add(item));
        return items;
    }

    public Item getItemByItemId(int itemId){

        Item item = this.jdbcTemplate.queryForObject(
                "SELECT * FROM products WHERE itemId = ?", new Object[] { itemId },
                (rs, rowNum) -> new Item(rs.getInt("itemId"), rs.getString("name"),
                        rs.getDouble("msrp"), rs.getDouble("salePrice"), rs.getLong("upc")
                        , rs.getString("shortDescription"), rs.getString("brandName")
                        , rs.getString("size"), rs.getString("color"), rs.getString("gender"))
        );

        return item;
    }


    public boolean deleteItem(int itemId){
        //TODO: Implement this CRUD function
        int x = this.jdbcTemplate.update("DELETE FROM products WHERE itemId = ?", itemId);
        if(x==1)
            return true;
        else
            return false;
    }


    public Collection<Item> getItemByKeyword(String keyword) {
        keyword = "%" + keyword + "%";
        Collection<Item> items = new ArrayList<>();
        String query = "SELECT * FROM products " +
                "WHERE LOWER(itemId) LIKE ? " +
                "OR LOWER(name) LIKE ? " +
                "OR LOWER(msrp) LIKE ? " +
                "OR LOWER(salePrice) LIKE ? " +
                "OR LOWER(upc) LIKE ? " +
                "OR LOWER(shortDescription) LIKE ? " +
                "OR LOWER(brandName) LIKE ? " +
                "OR LOWER(size) LIKE ? " +
                "OR LOWER(color) LIKE ? " +
                "OR LOWER(gender) LIKE ? ";
        this.jdbcTemplate.query(query, new Object [] { keyword, keyword, keyword, keyword, keyword, keyword, keyword,keyword, keyword, keyword },
                (rs, rowNum) -> new Item(rs.getInt("itemId"), rs.getString("name"),
                        rs.getDouble("msrp"), rs.getDouble("salePrice"), rs.getLong("upc")
                        , rs.getString("shortDescription"), rs.getString("brandName")
                        , rs.getString("size"), rs.getString("color"), rs.getString("gender"))
        ).forEach(item -> items.add(item));

        return items;
    }
}
