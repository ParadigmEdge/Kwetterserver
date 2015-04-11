package kwetter.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Tweet implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tweet;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    private String postedFrom;
    private String ownerName;
    private List<String> tags = new ArrayList();
    private List<String> mentions = new ArrayList();

    public Tweet() {
    }

    public Tweet(String tweet) {
        this.tweet = tweet;
    }
    
    public Tweet(String tweet, Date date, String from) {
        this.tweet = tweet;
        this.postDate = date;
        this.postedFrom = from;
    }
    
    public Tweet(String tweet, Date date, String from, String owner, List tags, List mentions) {
        this.tweet = tweet;
        this.postDate = date;
        this.postedFrom = from;
        this.ownerName = owner;
        this.tags = tags;
        this.mentions = mentions;    
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getDate(){
        return this.postDate;
    }
    
    public String getStringDate() {
        Date date = this.postDate;
        int dd = date.getDate();
        int mm = date.getMonth()+1;
        int yyyy = date.getYear();
        int HH = date.getHours();
        int MM = date.getMinutes();

        if(dd<10) {dd='0'+dd;} 
        if(mm<10) {mm='0'+mm;} 
        String convertedDate = ""+mm+'/'+dd+'/'+yyyy+' '+HH+':'+MM;
        return convertedDate;
    }

    public void setDate(Date datum) {
        this.postDate = datum;
    }

    public String getPostedFrom() {
        return postedFrom;
    }

    public void setPostedFrom(String from) {
        this.postedFrom = from;
    }
    
    public String getOwner() {
        return ownerName;
    }

    public void setOwner(String owner) {
        this.ownerName = owner;
    }
    
    public List getTags(){
        return this.tags;
    }
    
    public void setTags(List tags){
        this.tags = tags;
    }
    
    public List getMentions(){
        return this.mentions;
    }
    
    public void setMentions(List mentions){
        this.mentions = mentions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tweet != null ? tweet.hashCode()+ postDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tweet)) {
            return false;
        }
        Tweet other = (Tweet) object;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return "twitter.domain.Tweet[id=" + postDate.toString() + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
