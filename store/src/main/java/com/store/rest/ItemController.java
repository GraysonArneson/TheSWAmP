package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Path("/items")
public class ItemController extends HttpServlet  {


    // @Autowired
    private ItemService itemService = new ItemService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Collection<Item> getAllItems() {
        Collection<Item> items = itemService.getAllItems();
        return items;
//        String retString = "[";
//        int i =1;
//        for(Item item: items){
//            if(i++==items.size())
//                retString+= item.toString();
//            else
//                retString += item.toString() + ", ";
//        }
//        retString += "]";
//        // return items;
//        return retString;
    }

    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Item getItemByItemId(@PathParam("itemId") int itemId) {
        Item item = itemService.getItemByItemId(itemId);
        return item;
        // return item.toString();
    }

    @GET
    @Path("/search/{keyword}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Collection<Item> getItemByKeyword(@PathParam("keyword") String keyword) {
        keyword = keyword.toLowerCase();
        Collection<Item> items = itemService.getItemByKeyword(keyword);
        return items;
        // return item.toString();
    }

//    @GET
//    @RequestMapping(value={"/{keyword}", "/{itemId}"}, method = RequestMethod.GET)
//    @Produces(MediaType.APPLICATION_JSON_VALUE)
//    public String determine(@PathVariable("itemId") int itemId, @PathVariable("keyword") String keyword){
//        if(!(keyword != null)){
//            // int productId = Integer.parseInt(request.getParameter("productId"));
//            Item item = itemService.getItemByItemId(itemId);
////            String retString = "{\n";
////            int i = 1;
////            for(Item user: users){
////                if(i++==users.size())
////                    retString += "\"username\" : \"" + user + "\"";
////                else
////                    retString += "\"username\" : \"" + user + "\", \n\t";
////            }
////            retString += "\n}";
//            return item.toString();
//        }
//        else{
//            // String username = request.getParameter("username");
//            Collection<Item> items = itemService.getItemByKeyword(keyword);
//            String retString = "";
//            for(Item item: items) {
//                retString += item.toString();
//            }
//            return retString;
//        }
//
//    }




    @DELETE
    @Path("/{itemId}")
    public void deleteItem(@PathParam("itemId") int itemId) {
        itemService.deleteItem(itemId);
    }

}
