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
public class CustomerService {

    // @Autowired
    // JdbcTemplate jdbcTemp;
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/db_store";
    private String username = "springuser";
    private String password = "ThePassword";

    @Autowired
    private CustomerDAO customerDAO = new CustomerDAO(new JdbcTemplate(getDataSource()));


    public String getMsg( String msg) {
        return "Hello : " + msg;
    }

    public String getAllCustomers() {
        String retString = "";
        Collection<Customer> customers = customerDAO.getAllCustomers();
        for (Customer customer : customers) {
            retString += customer.toString();
        }

        return retString;
    }

    public Customer getCustomer(String username) {
        Customer customer = customerDAO.getCustomer(username);
        return customer;
    }

    public void createCustomer(Customer customer){
        customerDAO.createCustomer(customer);
    }

    public void updateCustomer(Customer customer){
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(String username) {
        boolean b = customerDAO.deleteCustomer(username);
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