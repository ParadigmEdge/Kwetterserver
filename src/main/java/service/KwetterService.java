package service;

import dao.JPA;
import java.util.List;
import dao.UserDAO;
import dao.ValueComparator;
import domain.Trend;
import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class KwetterService {
    
    @Inject 
    @JPA
    private UserDAO userDAO;

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
        for(User u : users){
            if(u.getName().equals(name)){
                found = u;
            }
        }
        return found;
    }

    public int countAllUsers() {
        return userDAO.usersCount();
    }
    
    public List<Tweet> getAllTweets(){
        List<Tweet> allTweets = new ArrayList();
        List<User> allUsers = this.getAllUsers();
        for(User u : allUsers){
            allTweets.addAll(u.getTweets());
        }
        return allTweets;
    }
    
    public List<Tweet> getTweetsFromUser(String name){
        List<Tweet> foundTweets = new ArrayList();
        User foundUser = this.findUserByName(name);
        if(foundUser!=null){
            foundTweets = (List<Tweet>) foundUser.getTweets();
        }
        return foundTweets;
    }
    
    public List<User> getFollowersFromUser(String name){ 
        User foundUser = this.findUserByName(name);
        List<User> foundFollowers = new ArrayList();
        if(foundUser != null){
            foundFollowers = (List<User>) foundUser.getFollowers();
        }
        return foundFollowers;
    }
    
    public List<Tweet> getTweetsWithMention(String mention){
        List<Tweet> found = new ArrayList();
        List<Tweet> allTweets = this.getAllTweets();
        
        for(Tweet t : allTweets){
            List<String> mentions = t.getMentions();
            for(String s : mentions){
                if(s.equals(mention)){
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

        ValueComparator<String, Integer> comparator = new ValueComparator<String,Integer>(map);
        Map<String, Integer> sortedMap = new TreeMap<String,Integer>(comparator);
        sortedMap.putAll(map);
        
        // List with trend objects
        List<Trend> trends = new ArrayList<Trend>();
        
        for(Map.Entry<String,Integer> entry : sortedMap.entrySet()) {
            Trend t = new Trend(entry.getKey(),entry.getValue());
            System.out.println(t.getName()+" - "+ t.getCount());
            trends.add(t);
        }
        
        List<String> sortedList = new ArrayList<String>(sortedMap.keySet());
        return trends;
    }
    
    private List getAllTags(){
        List<Tweet> allTweets = getAllTweets();
        List<String> allTags = new ArrayList();
        for(Tweet t : allTweets){
            allTags.addAll(t.getTags());
        }
        return allTags;
    }
    
    public boolean createTweet(String tweet, String owner){
        boolean succes = false;
        User u = findUserByName(owner);
        
        if(u instanceof User){
            Tweet t = buildTweet(tweet, owner);
            u.addTweet(t);
            userDAO.editUser(u);
            succes = true;
        }
        return succes;
    }
    
    public Tweet buildTweet(String tweet, String owner) {
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
        while (mentionMatcher.find()){
            System.out.println(mentionMatcher.group(1));
            mentions.add(mentionMatcher.group(1));
        }
        // new tweet (tweetcontent, date, postedFrom, owner, tagslist, mentionslist);
        Tweet t = new Tweet(tweet, new Date(), "unknown", owner, tags, mentions);
        return t;
    }
    
    
}