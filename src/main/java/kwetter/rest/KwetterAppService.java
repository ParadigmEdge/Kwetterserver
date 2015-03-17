/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kwetter.rest;

import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateful;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import service.KwetterService;

/**
 *
 * @author RY Jin
 */
@Path("/kwetter")
//@Stateful
public class KwetterAppService {
    KwetterService kwetter = new KwetterService();
    
    @GET
    @Path("/users")
    @Produces("application/xml,application/json")
    public List<User> listAllUsers(){
        return kwetter.getAllUsers();
    }
    
    @GET
    @Path("/users/{name}")
    @Produces("application/xml,application/json")
    public User userById(@PathParam("name") String name){
        return kwetter.findUserByName(name);
    }
    
    @GET
    @Path("/tweets")
    @Produces("application/xml,application/json")
    public List<Tweet> listUserTweetss(@PathParam("name") String name){
//        return kwetter.find(name);
//        return null;
        List<Tweet> found = kwetter.getAllTweets();
        return found;
    }
    
    @GET
    @Path("/tweets/{name}")
    @Produces("application/xml,application/json")
    public List<Tweet> listUserTweets(@PathParam("name") String name){
//        return kwetter.find(name);
//        return null;
        return kwetter.getTweetsFromUser(name);
    }
    
    @GET
    @Path("/stats/users-count")
    @Produces("application/xml,application/json")
    public String allUsers(){
//        return kwetter.findAll().size();
        return "" + kwetter.getAllUsers().size(); // return Long doesnt work..?
    }
    
//    @GET
//    @Path("/stats/users/active")
//    @Produces("application/json")
//    public long activeUsers(){
//        return 1L;
//    }
    
    
//    @POST
//    @Path("users/create/{user}")
//    @Consumes({"application/json"})
//    public void createUser(User user){
//        kwetter.create(user);
//    }
    
//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    public void editUser(@PathParam("id") Long id, User user){
//        kwetter.edit(user);
//    }
    
//    @DELETE
//    @Path("users/delete/{id}")
//    public void deleteUser(@PathParam("id") User user){
//        kwetter.remove(user);
//    }
}
