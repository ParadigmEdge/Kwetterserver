package dao;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
//import javax.enterprise.inject.Alternative;
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
        return this.getAllUsers().size();
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
    public void removeUser(User user) {
        em.remove(user);
    }
}
