package service;

import dao.JPA;
import java.util.List;
import dao.UserDAO;
import domain.Trend;
import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class KwetterService {
    
    @Inject 
    @JPA
    private UserDAO userDAO;

    public KwetterService() {
        System.out.println("publicKwetterService init");
    }
    
    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public void editUser(User user) {
        userDAO.editUser(user);
    }

    public void removeUser(User user) {
        userDAO.removeUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User findUserByName(String name) {
        return userDAO.findUserByName(name);
    }

    public int countAllUsers() {
        return userDAO.usersCount();
    }
    
    public List<Tweet> getAllTweets(){
        return userDAO.getAllTweets();
    }
    
    public List<Tweet> getTweetsFromUser(String name){
        return userDAO.getTweetsFromUserWithName(name);
    }
    
    public List<User> getFollowersFromUser(String name){ 
        return userDAO.getFollowersFromUserWithName(name);
    }
    
    public List<Tweet> getTweetsWithMention(String mention){
        return userDAO.getTweetsWithMentions(mention);
    }

    public List<Trend> getTrending() {
        return userDAO.getTrending();
    }
    
    public boolean createTweet(String tweet, String owner) {
        return userDAO.createTweetOfUser(tweet, owner);
    }
    
    
}