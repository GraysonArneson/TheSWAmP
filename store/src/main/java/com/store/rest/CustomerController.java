package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Controller
@Path("/customers")
public class CustomerController extends HttpServlet  {


    // @Autowired
    private CustomerService customerService = new CustomerService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }



    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomer(@PathParam("username") String username) {
        Customer customer = customerService.getCustomer(username);
        return customer;
        // return customer.toString();
    }

    @POST
    public void createCustomer(@QueryParam("fname") String fname, @QueryParam("lname") String lname, @QueryParam("username") String username, @QueryParam("email") String email) {
        Customer customer = new Customer(fname, lname, username, email);
        customerService.createCustomer(customer);
    }


    @PUT
    public void updateCustomer(@QueryParam("fname") String fname, @QueryParam("lname") String lname, @QueryParam("username") String username, @QueryParam("email") String email) {
        Customer customer = new Customer(fname, lname, username, email);
        customerService.updateCustomer(customer);
    }

    @DELETE
    @Path("/{username}")
    public void deleteCustomer(@PathParam("username") String username) {
        customerService.deleteCustomer(username);
    }

}
