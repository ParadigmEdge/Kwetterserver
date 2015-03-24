/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import domain.Tweet;
import domain.User;
import jdk.nashorn.internal.runtime.regexp.RegExp;

@Singleton
@Startup // causes the bean to be instantiated by the container when the application starts.
public class DataStorageBean {
       
    private final List<User> users = new ArrayList();
          
    @PostConstruct
    private void initUsers() {
        User u1 = new User("Hans", "http", "geboren 1", "assets/img/avatar_01.jpg");
        User u2 = new User("Frank", "httpF", "geboren 2", "assets/img/avatar_02.jpg");
        User u3 = new User("Tom", "httpT", "geboren 3", "assets/img/avatar_03.jpg");
        User u4 = new User("Sjaak", "httpS", "geboren 4", "assets/img/avatar_01.jpg");
        u1.addFollowing(u2);
        u1.addFollowing(u3);
        u1.addFollowing(u4);

        List tags = new ArrayList();
        List mentions = new ArrayList();
        List mentions2 = new ArrayList();
        tags.add("Hello");tags.add("World");
        mentions.add("Frank");mentions.add("Tom");
        mentions2.add("Hans");mentions2.add("Tom");
        
        Tweet t1 = new Tweet("Hallo", new Date(), "PC", "Hans", tags, mentions);
        Tweet t2 = new Tweet("Hallo again", new Date(), "PC", "Hans", tags, mentions);
        Tweet t3 = new Tweet("Hallo where are you", new Date(), "PC", "Hans", tags, mentions);
        Tweet t4 = new Tweet("Currently at the Rex", new Date(), "PC", "Frank", tags, mentions2);
        Tweet t5 = new Tweet("Im at the pathé watching a movie", new Date(), "PC", "Frank", tags, mentions2);
        
        u1.addTweet(t1);
        u1.addTweet(t2);
        u1.addTweet(t3);
        u2.addTweet(t4);
        u2.addTweet(t5);

        this.createUser(u1);
        this.createUser(u2);
        this.createUser(u3);
        this.createUser(u4);
    }  
      
    public int count() {
        return users.size();
    }

    public void createUser(User user) {
        users.add(user);
    }
    
    public void edit(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
    public List<User> findAll() {
        return new ArrayList(users);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
        //welke ID? lel.
    }
    
    public User findByName(String name){
        User found = null;
        for (User u : users){
            if(u.getName().equals(name)){
                found = u;
            }
        }
        return found;
    }
    
    //returns all tweets, TODO: only get tweets from user, and tweets with user mentioned?
    public List<Tweet> getAllTweets() {
        List<Tweet> found = new ArrayList();
        for (User u : users){
            if(u.getTweets().size()>0){
                found.addAll(u.getTweets());
            }
        }
        return found;
    }
    
    public List<Tweet> getTweetsFromUser(String name) {
        User foundUser = findByName(name);
        List<Tweet> foundTweets = new ArrayList();
        foundTweets.addAll(foundUser.getTweets());
        return foundTweets;
    }
    
    public boolean addTweetToUser(String tweet, String owner){
        boolean succes = false;
        User u = findByName(owner);
        if(u instanceof User){
            Tweet t = createTweet(tweet, owner);
            u.addTweet(t);
            succes = true;
        }
        return succes;
    }
    
    private Tweet createTweet(String tweet, String owner){
        String tweetContent = tweet;
        
        List<String> tags = new ArrayList();
        List<String> mentions = new ArrayList();
        
        String[] tagslistarr = tweetContent.split(" ");
        System.out.println(tagslistarr.length);
        System.out.println(tagslistarr);
        for (String t : tagslistarr) {
            System.out.println(t);
        }
//        for (String tagslistarr1 : tagslistarr) {
//            if (tagslistarr1.indexOf("#") == 0) {
//                String pattern = tagslistarr1;
//                String re = new RegExp(pattern, "g");
//                tweetContent = tweetContent.replace(re, "");
//                tags.add(tagslistarr1);
//            }
//            if (tagslistarr1.indexOf("@") == 0) {
//                String pattern = tagslistarr1;
//                String re = new RegExp(pattern, "g");
//                tweetContent = tweetContent.replace(re, "");
//                mentions.add(tagslistarr1);  
//            }
//        } 
        Tweet t = new Tweet(tweet, new Date(), "unknown", owner, tags, mentions);
        return t;

                //(old)JavaScript code:
        //for (int i = 0; i < tagslistarr.length; i++) {
        //    if(tagslistarr[i].indexOf("#") === 0){
        //      String pattern = tagslistarr[i],
        //      re = new RegExp(pattern, "g");
        //      tweetContent = tweetContent.replace(re, "");
        //
        //      tags.push(tagslistarr[i]);
        //    }
        //    if(tagslistarr[i].indexOf("@") === 0){
        //      String pattern = tagslistarr[i],
        //      re = new RegExp(pattern, "g");
        //      tweetContent = tweetContent.replace(re, "");
        //
        //      mentions.push(tagslistarr[i]);  
        //    }
        //} 
    }
    
    public List<User> getFollowersFromUser(String name) {
        User foundUser = findByName(name);
        List<User> foundFollowers = new ArrayList();
        foundFollowers.addAll(foundUser.getFollowing());
        return foundFollowers;
    }

    public List<Tweet> getTweetsWithMention(String mention){
        List<Tweet> found = new ArrayList();
        List<Tweet> allTweets = this.getAllTweets();
        
        for(Tweet t : allTweets){
            List<String> mentions = t.getMentions();
            for(String s : mentions){
                if(s.equals(mention)){
                    found.add(t);
                }
            }
        }
        return found;
    }

    List<String> getTrending() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
