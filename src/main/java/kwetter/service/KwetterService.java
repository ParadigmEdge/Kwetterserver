package kwetter.service;

import kwetter.dao.JPA;
import java.util.List;
import kwetter.dao.UserDAO;
import kwetter.dao.ValueComparator;
import kwetter.domain.Trend;
import kwetter.domain.Tweet;
import kwetter.domain.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;
//import kwetter.domain.Role;

@Stateless
public class KwetterService {

    @Inject
    @JPA
    private UserDAO userDAO;
    
    private Map<String, javax.websocket.Session> sessionMap = new HashMap<>();
    
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
        List<User> users = this.getAllUsers();
        User found = null;
        for (User u : users) {
            if (u.getName().equals(name)) {
                found = u;
            }
        }
        return found;
    }

    public int countAllUsers() {
        return userDAO.usersCount();
    }

    public List<Tweet> getAllTweets() {
        List<Tweet> allTweets = new ArrayList();
        List<User> allUsers = this.getAllUsers();
        for (User u : allUsers) {
            allTweets.addAll(u.getTweets());
        }
        return allTweets;
    }

    public List<Tweet> getTweetsFromUser(String name) {
        List<Tweet> foundTweets = new ArrayList();
        User foundUser = this.findUserByName(name);
        if (foundUser != null) {
            foundTweets = (List<Tweet>) foundUser.getTweets();
        }
        return foundTweets;
    }

    public List<User> getFollowersFromUser(String name) {
        User foundUser = this.findUserByName(name);
        List<User> foundFollowers = new ArrayList();
        if (foundUser != null) {
            foundFollowers = (List<User>) foundUser.getFollowers();
        }
        return foundFollowers;
    }

    public List<Tweet> getTweetsWithMention(String mention) {
        List<Tweet> found = new ArrayList();
        List<Tweet> allTweets = this.getAllTweets();

        for (Tweet t : allTweets) {
            List<String> mentions = t.getMentions();
            for (String s : mentions) {
                if (s.equals(mention)) {
                    found.add(t);
                }
            }
        }
        return found;
    }

    public List<Trend> getTrending() {
        List<String> allTags = getAllTags();
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String s : allTags) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        ValueComparator<String, Integer> comparator = new ValueComparator<String, Integer>(map);
        Map<String, Integer> sortedMap = new TreeMap<String, Integer>(comparator);
        sortedMap.putAll(map);

        // List with trend objects
        List<Trend> trends = new ArrayList<Trend>();

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            Trend t = new Trend(entry.getKey(), entry.getValue());
            System.out.println(t.getName() + " - " + t.getCount());
            trends.add(t);
        }

        List<String> sortedList = new ArrayList<String>(sortedMap.keySet());
        return trends;
    }

    private List getAllTags() {
        List<Tweet> allTweets = getAllTweets();
        List<String> allTags = new ArrayList();
        for (Tweet t : allTweets) {
            allTags.addAll(t.getTags());
        }
        return allTags;
    }

    public boolean createTweet(String tweet, String owner) {
        boolean succes = false;
        User u = findUserByName(owner);

        if (u instanceof User) {
            Tweet t = buildTweet(tweet, owner);
            u.addTweet(t);
            userDAO.editUser(u);
            succes = true;
        }
        return succes;
    }

    private Tweet buildTweet(String tweet, String owner) {
        String tweetContent = tweet;
        // tags and mentions lists
        List<String> tags = new ArrayList();
        List<String> mentions = new ArrayList();
        // regular expressions
        Pattern HASHTAG_PATTERN = Pattern.compile("#(\\w+)");
        Matcher hashtagMatcher = HASHTAG_PATTERN.matcher(tweetContent);
        Pattern MENTION_PATTERN = Pattern.compile("@(\\w+)");
        Matcher mentionMatcher = MENTION_PATTERN.matcher(tweetContent);

        while (hashtagMatcher.find()) {
            System.out.println(hashtagMatcher.group(1));
            tags.add(hashtagMatcher.group(1));
        }
        while (mentionMatcher.find()) {
            System.out.println(mentionMatcher.group(1));
            mentions.add(mentionMatcher.group(1));
        }
        // new tweet (tweetcontent, date, postedFrom, owner, tagslist, mentionslist);
        Tweet t = new Tweet(tweet, new Date(), "unknown", owner, tags, mentions);
        return t;
    }

    public void setSession(String username, javax.websocket.Session session) {
        this.sessionMap.put(username, session);
    }

    public javax.websocket.Session getSession(String username) {
        return this.sessionMap.get(username);
    }

    public void removeSession(String username) {
        this.sessionMap.remove(username);
    }

    public void initUsers() {
//        Role role = new Role("normal");
//        Role adminRole = new Role("admin");
//        
        User u1 = new User("Hans", "http", "geboren 1", "assets/img/avatar_01.jpg");
        User u2 = new User("Frank", "httpF", "geboren 2", "assets/img/avatar_02.jpg");
        User u3 = new User("Tom", "httpT", "geboren 3", "assets/img/avatar_03.jpg");
        User u4 = new User("Sjaak", "httpS", "geboren 4", "assets/img/avatar_01.jpg");
        u1.addFollowing(u2);
        u1.addFollower(u3);
//        
//        u1.getRoles().add(role);
//        u1.getRoles().add(adminRole);
//        u2.getRoles().add(role);
//        u3.getRoles().add(role);
//        u4.getRoles().add(role);

        List tags = new ArrayList();
        List tags2 = new ArrayList();
        List mentions = new ArrayList();
        List mentions2 = new ArrayList();
        tags.add("Netbeans");
        tags2.add("Hello");
        tags2.add("World");
        mentions.add("Frank");
        mentions.add("Tom");
        mentions2.add("Hans");
        mentions2.add("Tom");

        Tweet t1 = new Tweet("Hallo", new Date(), "PC", "Hans", tags, mentions);
        Tweet t2 = new Tweet("Hallo again", new Date(), "PC", "Hans", tags, mentions);
        Tweet t3 = new Tweet("Hallo where are you", new Date(), "PC", "Hans", tags2, mentions);
        Tweet t4 = new Tweet("Currently at the Rex", new Date(), "PC", "Frank", tags, mentions2);
        Tweet t5 = new Tweet("Im at the pathé watching a movie", new Date(), "PC", "Frank", tags, mentions2);

        u1.addTweet(t1);
        u1.addTweet(t2);
        u1.addTweet(t3);
        u2.addTweet(t4);
        u2.addTweet(t5);

        this.createUser(u1);
        this.createUser(u2);
        this.createUser(u3);
        this.createUser(u4);
    }

}
