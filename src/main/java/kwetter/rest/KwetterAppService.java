/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kwetter.rest;

import domain.Trend;
import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import service.KwetterService;

/**
 *
 * @author RY Jin
 */
@Path("/kwetter")
@Stateful
public class KwetterAppService {
    @Inject
    KwetterService kwetter;
    
    @GET
    @Path("/users")
    @Produces("application/json")
    public List<User> listAllUsers(){
        return kwetter.getAllUsers();
    }
    
    @GET
    @Path("/users/{name}")
    @Produces("application/json")
    public User userById(@PathParam("name") String name){
        return kwetter.findUserByName(name);
    }

    @GET
    @Path("/users/{name}/tweets")
    @Produces("application/json")
    public List<Tweet> getTweets(@PathParam("name") String name){
        return kwetter.getTweetsFromUser(name);
    }
    
    
    @GET
    @Path("/users/{name}/followers")
    @Produces("application/json")
    public List<User> getFollowers(@PathParam("name") String name){
        return kwetter.getFollowersFromUser(name);
    }
    
    @GET
    @Path("/tweets")
    @Produces("application/json")
    public List<Tweet> getAllTweets(){
        return kwetter.getAllTweets();
    }
    
    @GET
    @Path("/tweets/mention/{name}")
    @Produces("application/json")
    public List<Tweet> getTweetsWithMention(@PathParam("name") String name){
        return kwetter.getTweetsWithMention(name);
    }
    
    @POST
    @Path("/tweets/{id}")
    @Consumes("application/json")
    public void createTweet(String tweet, @PathParam("id") String username) {
        String tweetContent = tweet;
        String tweetOwner = username;
        System.out.println(tweetContent); 
        System.out.println(tweetOwner);
        boolean passed = kwetter.createTweet(tweetContent,tweetOwner);
    }
    
    @GET
    @Path("/stats/trending")
    @Produces("application/json")
    public List<Trend> getTrending(){
        // WHY YOU NOT WORKING?!?!
        // Trend object has @xml element..
        // also not working with a list of strings...
//        return kwetter.getTrending();
        return null;
    }
            
    @GET
    @Path("/stats/users-count")
    @Produces("application/json")
    public String allUsers(){
        return "" + kwetter.getAllUsers().size(); // return Long doesnt work..needs xmlrootelement on all resources.
    }
}
