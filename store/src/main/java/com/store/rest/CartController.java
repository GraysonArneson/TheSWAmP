package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;

import javax.ws.rs.*;
import java.util.Collection;


import com.store.model.*;

@Controller
@Path("/carts")
public class CartController extends HttpServlet  {


    // @Autowired
    private CartService cartService = new CartService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }



//    @GET
//    @Path("/{username}")
//    @Produces(MediaType.APPLICATION_JSON_VALUE)
//    public String getCustomer(@PathParam("username") String username) {
//        Customer customer = customerService.getCustomer(username);
//
//        return customer.toString();
//    }

    @POST
    public void addItem(@QueryParam("productId") int productId, @QueryParam("username") String username) {
        cartService.addItem(username,productId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public String determine(@QueryParam("productId") int productId, @QueryParam("username") String username){
        if(!(username != null)){
            // int productId = Integer.parseInt(request.getParameter("productId"));
            Collection<String> users = cartService.getProductPurchaseUsers(productId);
            String retString = "{\n";
            int i = 1;
            for(String user: users){
                if(i++==users.size())
                    retString += "\"username\" : \"" + user + "\"";
                else
                    retString += "\"username\" : \"" + user + "\", \n\t";
            }
            retString += "\n}";
            return retString;
        }
        else{
            // String username = request.getParameter("username");
            Collection<Cart> users = cartService.showCart(username);
            String retString = "[\n";
            int i =1;
            for(Cart cart: users) {
                if(i++==users.size())
                    retString += cart.toString() + "\n}";
                else
                    retString += cart.toString() + "\n},\n";
            }
            retString += "\n]";
            return retString;
        }

    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON_VALUE)
//     // @QueryParam("productId")
//    // @RequestMapping(params = "productId")
//    public Collection<String> getProductPurchaseUsers(@RequestParam("productId") int productId) {
//        Collection<String> users = cartService.getProductPurchaseUsers(productId);
//        return users;
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON_VALUE)
////    @QueryParam("username")
//    // @RequestMapping(params = "username")
//    public Collection<Cart> showCart(@RequestParam("username") String username) {
//        Collection<Cart> carts = cartService.showCart(username);
//        return carts;
//
//    }

    @DELETE
    public void removeFromCart(@QueryParam("cartId") int cartId, @QueryParam("productId") int productId) {
        cartService.removeFromCart(cartId, productId);
    }

    @PUT
    @Path("/purchase/{cartId}")
    public void purchaseCart(@PathParam("cartId") int cartId) {
        cartService.purchaseCart(cartId);
    }




//    @PUT
//    public void updateCustomer(@QueryParam("fname") String fname, @QueryParam("lname") String lname, @QueryParam("username") String username, @QueryParam("email") String email) {
//        Customer customer = new Customer(fname, lname, username, email);
//        customerService.updateCustomer(customer);
//    }
//
//    @DELETE
//    @Path("/{username}")
//    public void deleteCustomer(@PathParam("username") String username) {
//        customerService.deleteCustomer(username);
//    }

}
