package dao;

import domain.User;
import java.util.List;

public interface UserDAO {
    int usersCount();

    void createUser(User user);

    void editUser(User user);

    List<User> getAllUsers();

    User findUserById(Long id);
    
    void removeUser(User user);
}
