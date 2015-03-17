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

@Singleton
//@ConcurrencyManagement(BEAN) // @ConcurrencyManagement(BEAN) are responsible for their own thread-safety
@Startup // causes the bean to be instantiated by the container when the application starts.
public class DataStorageBean {
       
    private final List<User> users = new ArrayList();
          
    @PostConstruct
    private void initUsers() {
        User u1 = new User("Hans", "http", "geboren 1");
        User u2 = new User("Frank", "httpF", "geboren 2");
        User u3 = new User("Tom", "httpT", "geboren 3");
        User u4 = new User("Sjaak", "httpS", "geboren 4");
        u1.addFollowing(u2);
        u1.addFollowing(u3);
        u1.addFollowing(u4);

        Tweet t1 = new Tweet("Hallo", new Date(), "PC");
        Tweet t2 = new Tweet("Hallo again", new Date(), "PC");
        Tweet t3 = new Tweet("Hallo where are you", new Date(), "PC");
        
        List tags = new ArrayList();
        List mentions = new ArrayList();
        tags.add("tag1");tags.add("tag2");
        mentions.add("mention1");mentions.add("mention2");
        t1.setTags(tags);
        t1.setMentions(mentions);
        
        u1.addTweet(t1);
        u1.addTweet(t2);
        u1.addTweet(t3);

        this.create(u1);
        this.create(u2);
        this.create(u3);
        this.create(u4);
    }  
//     
//    public DataStorageBean(){
//        initUsers();
//    }
      
    public int count() {
        return users.size();
    }

    public void create(User user) {
        users.add(user);
    }
    
    public void edit(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
    public List<User> findAll() {
        return new ArrayList(users);
    }

    public void remove(User user) {
        users.remove(user);
    }

    public User findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
        //welke ID? 
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
    
    public List<Tweet> getAllTweets() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Tweet> found = new ArrayList();
        for (User u : users){
            if(u.getTweets().size()>0){
                found.addAll(u.getTweets());
            }
        }
        return found;
    }
    
    public List<Tweet> getTweetsFromUserWithName(String name) {
        User foundUser = findByName(name);
        List<Tweet> foundTweets = new ArrayList();
        foundTweets.addAll(foundUser.getTweets());
        return foundTweets;
    }
}
