package dao;

import domain.Tweet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import domain.User;

public class UserDAOCollectionImpl implements UserDAO {

    private List<User> users;

    public UserDAOCollectionImpl() {
        users = new ArrayList();
    }

    @Override
    public int usersCount() {
        return users.size();
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public void editUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void removeUser(User user) {
        users.remove(user);
    }
    
    @Override
    public List<User> getAllUsers() {
        return new ArrayList(users);
    }

    @Override
    public User findUserByName(String name) {
        User found = null;
        for (User u : users){
            if(u.getName().equals(name)){
                found = u;
            }
        }
        return found;
    }
    
    @Override
    public User findUserById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
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
    
//    @Override
//    public List<Tweet> getTweetsFromUserWithId(Long id) {
////        User foundUser = findUserById(id);
////        return (List<Tweet>) foundUser.getTweets();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    @Override
    public List<Tweet> getTweetsFromUserWithName(String name) {
        User foundUser = findUserByName(name);
        List<Tweet> foundTweets = new ArrayList();
        foundTweets.addAll(foundUser.getTweets());
        return foundTweets;
    }
}
