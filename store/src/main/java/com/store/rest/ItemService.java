package com.store.rest;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class ItemService {

    // @Autowired
    // JdbcTemplate jdbcTemp;
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/db_store";
    private String username = "springuser";
    private String password = "ThePassword";

    @Autowired
    private ItemDAO itemDAO = new ItemDAO(new JdbcTemplate(getDataSource()));


    public String getMsg( String msg) {
        return "Hello : " + msg;
    }

    public Collection<Item> getAllItems() {
        Collection<Item> items = itemDAO.getAllItems();

        return items;
    }

    public Item getItemByItemId(int itemId) {
        Item item = itemDAO.getItemByItemId(itemId);
        return item;
    }

    public Collection<Item> getItemByKeyword(String keyword) {
        Collection<Item> items = itemDAO.getItemByKeyword(keyword);
        return items;
    }


    public void deleteItem(int itemId) {
        boolean b = itemDAO.deleteItem(itemId);
    }

    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(url);

        return ds;
    }



}