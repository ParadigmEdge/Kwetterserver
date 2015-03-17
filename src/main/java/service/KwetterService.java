package service;

import java.util.Date;
import java.util.List;
import dao.UserDAO;
import dao.UserDAOCollectionImpl;
import domain.Tweet;
import domain.User;
import java.util.ArrayList;
//import javax.ejb.Stateless;
//
//@Stateless
public class KwetterService {

    private UserDAO userDAO = new UserDAOCollectionImpl();
    
    public KwetterService() {
        initUsers();
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
    
//    public List<Tweet> getTweetsFromUser(Long id){
//        return userDAO.getTweetsFromUserWithId(id);
//    }
    
    public List<Tweet> getTweetsFromUser(String name){
        return userDAO.getTweetsFromUserWithName(name);
    }

    private void initUsers() {
        User u1 = new User("Hans", "http", "geboren 1");
        User u2 = new User("Frank", "httpF", "geboren 2");
        User u3 = new User("Tom", "httpT", "geboren 3");
        User u4 = new User("Sjaak", "httpS", "geboren 4");
        u1.addFollowing(u2);
        u1.addFollowing(u3);
        u1.addFollowing(u4);

        Tweet t1 = new Tweet("Hallo", new Date(), "PC");
        List tags = new ArrayList();
        List mentions = new ArrayList();
        tags.add("tag1");tags.add("tag2");
        mentions.add("mention1");mentions.add("mention2");
        t1.setTags(tags);
        t1.setMentions(mentions);
        Tweet t2 = new Tweet("Hallo again", new Date(), "PC");
        Tweet t3 = new Tweet("Hallo where are you", new Date(), "PC");
        u1.addTweet(t1);
        u1.addTweet(t2);
        u1.addTweet(t3);

        userDAO.createUser(u1);
        userDAO.createUser(u2);
        userDAO.createUser(u3);
        userDAO.createUser(u4);
    }
}