package dao;

import domain.Trend;
import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author RY Jin
 */
@Stateless
@JPA
//@Alternative
public class UserDAO_JPAImpl implements UserDAO{

    private final String PERSISTENCE_UNIT = "MySQLKwetterServicePU";
    
    @PersistenceContext(unitName = PERSISTENCE_UNIT)
    private EntityManager em;
    
    @Override
    public int usersCount() {
        return this.usersCount();
    }
    
    @Override
    public void createUser(User user) {
        em.persist(user);
        System.out.println("JPA ENTITY MANAGER PERSIST: " + user);
    }

    @Override
    public void editUser(User user) {
        em.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        Query q = em.createNamedQuery("User.findAll");
        return q.getResultList();
    }

    @Override
    public User findUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findUserByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    @Override
    public List<Tweet> getAllTweets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tweet> getTweetsFromUserWithName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createTweetOfUser(String tweet, String owner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getFollowersFromUserWithName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tweet> getTweetsWithMentions(String mention) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Trend> getTrending() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
