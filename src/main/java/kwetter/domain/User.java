package kwetter.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name = "USERS")
@NamedQueries({@NamedQuery(name="User.findAll", query="SELECT u FROM USERS u")})
public class User implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    private String web;
    private String bio;
    private String avatar;

    @JoinTable(
        joinColumns = {
            @JoinColumn(name = "followersUserName", referencedColumnName = "name")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "followingUserName", referencedColumnName = "name")
        }
    )
        
    
    @ManyToMany(mappedBy ="followers",fetch = FetchType.EAGER)
    private Collection<User> following = new ArrayList();
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> followers = new ArrayList();
    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
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
    //BIO
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    //NAME
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //WEB
    public String getWeb() {
        return web;
    }
    public void setWeb(String web) {
        this.web = web;
    }
    //AVATAR
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    //FOLLOWINGS
    public Collection<User> getFollowing() {
        return Collections.unmodifiableCollection(following);
    }
    public void setFollowing(Collection<User> following) {
        this.following = following;
    }
    public Boolean addFollowing(User following){
        return this.following.add(following);
    }
    //FOLLOWERS
    public Collection<User> getFollowers() {
        return Collections.unmodifiableCollection(followers);
    }
    public void setFollower(Collection<User> followers) {
        this.followers = followers;
    }
    public Boolean addFollower(User follower){
        return this.followers.add(follower);
    }
    //TWEETS
    public Collection<Tweet> getTweets() {
        return Collections.unmodifiableCollection(tweets);
    }
    public void setTweets(Collection<Tweet> tweets) {
        this.tweets = tweets;
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
