package dao;

import domain.Tweet;
import java.util.List;
import domain.User;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserDAOCollectionImpl implements UserDAO {
    @Inject DataStorageBean dataStorageBean;
    //DataStorageBean dataStorageBean = new DataStorageBean();
//    DataStorageBean dataStorageBean = lookupDataStorageBeanBean();
    
    public UserDAOCollectionImpl() {
//        users = new ArrayList();
    }

    @Override
 
    public int usersCount() {
//        return users.size();
        return dataStorageBean.count();
    }

    @Override
    public void createUser(User user) {
//        users.add(user);
        dataStorageBean.create(user);
    }

    @Override
    public void editUser(User user) {
        dataStorageBean.edit(user);
    }
    
    @Override
    public void removeUser(User user) {
//        users.remove(user);
        dataStorageBean.remove(user);
    }
    
    @Override
    public List<User> getAllUsers() {
//        return new ArrayList(users);
        return dataStorageBean.findAll();
    }

    @Override
    public User findUserByName(String name) {
//        User found = null;
//        for (User u : users){
//            if(u.getName().equals(name)){
//                found = u;
//            }
//        }
//        return found;
        return dataStorageBean.findByName(name);
    }
    
    @Override
    public User findUserById(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return dataStorageBean.findById(id);
    }
    
    @Override
    public List<Tweet> getAllTweets() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        List<Tweet> found = new ArrayList();
//        for (User u : users){
//            if(u.getTweets().size()>0){
//                found.addAll(u.getTweets());
//            }
//        }
//        return found;
        return dataStorageBean.getAllTweets();
    }
    
//    @Override
//    public List<Tweet> getTweetsFromUserWithId(Long id) {
////        User foundUser = findUserById(id);
////        return (List<Tweet>) foundUser.getTweets();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    @Override
    public List<Tweet> getTweetsFromUserWithName(String name) {
//        User foundUser = findUserByName(name);
//        List<Tweet> foundTweets = new ArrayList();
//        foundTweets.addAll(foundUser.getTweets());
//        return foundTweets;
        return dataStorageBean.getTweetsFromUserWithName(name);
    }

//    private DataStorageBean lookupDataStorageBeanBean() {
//        try {
//            Context c = new InitialContext();
//            return (DataStorageBean) c.lookup("java:global/com.mycompany_Kwetter_Server_war_1.0-SNAPSHOT/DataStorageBean!dao.DataStorageBean");
//        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
//            throw new RuntimeException(ne);
//        }
//    }
}
