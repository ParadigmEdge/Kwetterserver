package domain;

import domain.Tweet;
import domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-11T21:36:29")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, User> followers;
    public static volatile SingularAttribute<User, String> web;
    public static volatile CollectionAttribute<User, User> following;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> bio;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile CollectionAttribute<User, Tweet> tweets;

}