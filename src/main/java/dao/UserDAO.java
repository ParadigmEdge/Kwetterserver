package dao;

import domain.Trend;
import domain.User;
import domain.Tweet;
import java.util.List;

public interface UserDAO {

    int usersCount();

    void createUser(User user);

    void editUser(User user);

    List<User> getAllUsers();

    User findUserById(Long id);
    
    User findUserByName(String name);

    void removeUser(User user);
    
    List<Tweet> getAllTweets();
    
    List<Tweet> getTweetsFromUserWithName(String name);
    
    boolean createTweetOfUser(String tweet, String owner);
    
    List<User> getFollowersFromUserWithName(String name);
    
    List<Tweet> getTweetsWithMentions(String mention);

    List<Trend> getTrending();
}
