package kwetter.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RY Jin
 */

// NOT USED - shit doesn't work
@XmlRootElement
public class Trend {
    
    private String name;
    private int count;
    
    public Trend(String n, int c){
        this.name=n;
        this.count=c;
    }
    
    public void setName(String n){
        this.name = n;
    }
    
    public void setCount(int i){
        this.count = i;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getCount(){
        return this.count;
    }
    
    public void increment(){
        this.count++;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() + count : 0);
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
        return "twitter.domain.Trend[name=" + name + "]";
    }
}
