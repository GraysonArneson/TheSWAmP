package com.store.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

public class CustomerDAO {

    private JdbcTemplate jdbcTemplate;

    public CustomerDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public void createCustomer(Customer customer){
        //TODO: Implement this CRUD function
        this.jdbcTemplate.update("INSERT INTO customers (fname, lname, username, email) VALUES (?, ?, ?, ? )", customer.getFname(), customer.getLname(), customer.getUsername(), customer.getEmail());
    }

    public Customer getCustomer(String username){
        String query = "SELECT  * FROM customers WHERE username = ? ";
        Customer customer = this.jdbcTemplate.queryForObject(query, new Object[]{ username },
                (rs, rowNum) -> new Customer(rs.getString("fname"), rs.getString("lname")
                        , rs.getString("username"), rs.getString("email")));
        return customer;
    }

    public Collection<Customer> getAllCustomers(){
        Collection<Customer> customers = new ArrayList<Customer>();
        this.jdbcTemplate.query(
                "SELECT * FROM customers", new Object[] { },
                (rs, rowNum) -> new Customer(rs.getString("fname"), rs.getString("lname"), rs.getString("username"), rs.getString("email"))
        ).forEach(customer -> customers.add(customer));

        return customers;
    }

    public void updateCustomer(Customer customer){
        this.jdbcTemplate.update("UPDATE customers SET fname = ?, lname = ?, email = ? WHERE username = ?", customer.getFname(), customer.getLname(), customer.getEmail(), customer.getUsername());
    }

    public boolean deleteCustomer(String username){
        int x = this.jdbcTemplate.update("DELETE FROM customers WHERE username = ?", username);
        if(x==1)
            return true;
        else
            return false;
    }
}