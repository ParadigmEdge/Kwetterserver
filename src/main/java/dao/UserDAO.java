package dao;

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
    
//    List<Tweet> getTweetsFromUserWithId(Long id);
    
    List<Tweet> getTweetsFromUserWithName(String name);
}
