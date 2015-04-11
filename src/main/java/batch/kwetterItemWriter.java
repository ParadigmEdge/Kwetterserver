/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import domain.Tweet;
import domain.User;
import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import service.KwetterService;

/**
 *
 * @author Yongyi Jin
 */
@Dependent
@Named("kwetterItemWriter")
public class kwetterItemWriter extends AbstractItemWriter {
    
    @Inject
    KwetterService kwetterService;

    @Override
    public void writeItems(List list) {
        for (Object resultArray : list) {
            //System.out.println(Arrays.deepToString((Object[])resultArray));
            Tweet tweet = (Tweet) ((Object[]) resultArray)[0];
            String name = (String) ((Object[]) resultArray)[1];
            if (tweet == null || name == null) {
                System.out.println("----- WRITING DONE -----");
                return;
            }
            User user = kwetterService.findUserByName(name);
            if (user == null) {
                user = new User(name, "http://", "No bio");
                kwetterService.createUser(user);
            }
            user.addTweet(tweet);
            System.out.println("----- WRITING TWEET -----");
            System.out.println("name: "+name+" tweet: "+tweet);
            kwetterService.editUser(user);
        }
    }
}
