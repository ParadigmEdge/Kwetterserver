/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kwetter.dao;

import kwetter.domain.Trend;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import kwetter.domain.Tweet;
import kwetter.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
@Startup // causes the bean to be instantiated by the container when the application starts.
public class DataStorageBean {
       
    private final List<User> users = new ArrayList();
          
    @PostConstruct
    private void initUsers() {
        User u1 = new User("Hans", "http", "geboren 1", "assets/img/avatar_01.jpg");
        User u2 = new User("Frank", "httpF", "geboren 2", "assets/img/avatar_02.jpg");
        User u3 = new User("Tom", "httpT", "geboren 3", "assets/img/avatar_03.jpg");
        User u4 = new User("Sjaak", "httpS", "geboren 4", "assets/img/avatar_01.jpg");
        u1.addFollowing(u2);
        u1.addFollowing(u3);
        u1.addFollowing(u4);

        List tags = new ArrayList();
        List tags2 = new ArrayList();
        List mentions = new ArrayList();
        List mentions2 = new ArrayList();
        tags.add("Netbeans");
        tags2.add("Hello");tags2.add("World");
        mentions.add("Frank");mentions.add("Tom");
        mentions2.add("Hans");mentions2.add("Tom");
        
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
      
    public int count() {
        return users.size();
    }

    public void createUser(User user) {
        users.add(user);
    }
    
    public void edit(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
    public List<User> findAll() {
        return new ArrayList(users);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
        //welke ID? lel.
    }
    
    public User findByName(String name){
        User found = null;
        for (User u : users){
            if(u.getName().equals(name)){
                found = u;
            }
        }
        return found;
    }
    
    //returns all tweets, TODO: only get tweets from user, and tweets with user mentioned?
    public List<Tweet> getAllTweets() {
        List<Tweet> found = new ArrayList();
        for (User u : users){
            if(u.getTweets().size()>0){
                found.addAll(u.getTweets());
            }
        }
        return found;
    }
    
    public List<Tweet> getTweetsFromUser(String name) {
        User foundUser = findByName(name);
        List<Tweet> foundTweets = new ArrayList();
        foundTweets.addAll(foundUser.getTweets());
        return foundTweets;
    }
    
    public boolean addTweetToUser(String tweet, String owner){
        boolean succes = false;
        User u = findByName(owner);
        if(u instanceof User){
            Tweet t = createTweet(tweet, owner);
            u.addTweet(t);
            succes = true;
        }
        return succes;
    }
    
    private Tweet createTweet(String tweet, String owner){
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
    
    public List<User> getFollowersFromUser(String name) {
        User foundUser = findByName(name);
        List<User> foundFollowers = new ArrayList();
        foundFollowers.addAll(foundUser.getFollowing());
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
        
//        System.out.println(map);
//        System.out.println(sortedMap);
//        System.out.println(sortedList); //hmmm, value with same count gives problems
        
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
}
