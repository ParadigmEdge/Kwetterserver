package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name = "USERS")
@NamedQueries({
    @NamedQuery(name="User.findAll", query="SELECT u FROM USERS u")
})
public class User implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String web;
    private String bio;
    private String avatar;

    private Collection<User> following = new ArrayList();
    private Collection<User> followers = new ArrayList();
    private Collection<Tweet> tweets = new ArrayList();

    public User() {
    }

    public User(String naam) {
        this.name = naam;
    }

    public User(String naam, String web, String bio) {
        this.name = naam;
        this.web = web;
        this.bio = bio;
    }
    
    public User(String naam, String web, String bio, String avatar){
        this.name = naam;
        this.web = web;
        this.bio = bio;
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Collection<User> getFollowing() {
        return Collections.unmodifiableCollection(following);
    }

    public void setFollowing(Collection<User> following) {
        this.following = following;
    }
    
    public Collection<User> getFollower() {
        return Collections.unmodifiableCollection(followers);
    }

    public void setFollower(Collection<User> followers) {
        this.followers = followers;
    }

    public Collection<Tweet> getTweets() {
        return Collections.unmodifiableCollection(tweets);
    }

    public void setTweets(Collection<Tweet> tweets) {
        this.tweets = tweets;
    }
    
    public Boolean addFollowing(User following){
        return this.following.add(following);
    }

    public Boolean addTweet(Tweet tweet){
        tweet.setOwner(this.getName());
        return this.tweets.add(tweet);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() + bio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the name fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return this.hashCode()==other.hashCode();
    }

    @Override
    public String toString() {
        return "twitter.domain.User[naam=" + name + "]";
    }

}
