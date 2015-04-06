/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author frankcoenen
 */

@Singleton
@Startup
public class TestData {
        
    @Inject
    private KwetterService ks;
      
    @PostConstruct
    void initUsers() {
//        User u1 = new User("Hans", "http", "geboren 1", "assets/img/avatar_01.jpg");
//        List tags = new ArrayList();
//        List mentions = new ArrayList();
//       
//        Tweet t1 = new Tweet("Hallo", new Date(), "PC", "Hans", tags, mentions);
//        u1.addTweet(t1);
//        
//        ks.createUser(u1);

        User u1 = new User("Hans", "http", "geboren 1", "assets/img/avatar_01.jpg");
        User u2 = new User("Frank", "httpF", "geboren 2", "assets/img/avatar_02.jpg");
        User u3 = new User("Tom", "httpT", "geboren 3", "assets/img/avatar_03.jpg");
        User u4 = new User("Sjaak", "httpS", "geboren 4", "assets/img/avatar_01.jpg");
//        u1.addFollowing(u2);
//        u1.addFollowing(u3);
//        u1.addFollowing(u4);

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

        ks.createUser(u1);
        ks.createUser(u2);
        ks.createUser(u3);
        ks.createUser(u4);
    } 
}
