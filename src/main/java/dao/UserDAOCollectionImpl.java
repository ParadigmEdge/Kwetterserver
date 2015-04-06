package dao;

import domain.Trend;
import domain.Tweet;
import java.util.List;
import domain.User;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author RY Jin
 */
@Stateless
@Collection
public class UserDAOCollectionImpl implements UserDAO {
    @Inject 
    private DataStorageBean dataStorageBean;
    
    public UserDAOCollectionImpl() {
    }

    @Override
    public int usersCount() {
        return dataStorageBean.count();
    }

    @Override
    public void createUser(User user) {
        dataStorageBean.createUser(user);
    }

    @Override
    public void editUser(User user) {
        dataStorageBean.edit(user);
    }
    
    @Override
    public void removeUser(User user) {
        dataStorageBean.removeUser(user);
    }
    
    @Override
    public List<User> getAllUsers() {
        return dataStorageBean.findAll();
    }

    public User findUserByName(String name) {
        return dataStorageBean.findByName(name);
    }
    
    @Override
    public User findUserById(Long id) {
        return dataStorageBean.findById(id);
    }
    
    public List<Tweet> getAllTweets() {
        return dataStorageBean.getAllTweets();
    }
    
    public List<Tweet> getTweetsFromUserWithName(String name) {
        return dataStorageBean.getTweetsFromUser(name);
    }
    
    public List<User> getFollowersFromUserWithName(String name) {
        return dataStorageBean.getFollowersFromUser(name);
    }
    
    public List<Tweet> getTweetsWithMentions(String mention){
        return dataStorageBean.getTweetsWithMention(mention);
    }

    public List<Trend> getTrending() {
        return dataStorageBean.getTrending();
    }

    public boolean createTweetOfUser(String tweet, String owner) {
        return dataStorageBean.addTweetToUser(tweet, owner);
    }
}
