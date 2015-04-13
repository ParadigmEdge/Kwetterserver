package kwetter.jms;

import kwetter.domain.Tweet;
import kwetter.domain.User;
import kwetter.service.KwetterService;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDrivenContext;

/**
 * @author RY Jin Async Message receiving bean.
 */
@MessageDriven(mappedName = "jms/Queue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode",
            propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", 
            propertyValue = "myQueue")
})
public class kwetterJMS implements MessageListener {

    //resource to call the setRollbackOnly method to handle exceptions 
    @Resource
    private MessageDrivenContext mdc;

    @Inject
    private KwetterService service;

    @Override
    public void onMessage(Message message) {
        try {
            String username = message.getStringProperty("username");
            String tweetContent = message.getStringProperty("content");

            User user = service.findUserByName(username);
            if (user != null) {
                try {
                    Tweet tweet = new Tweet(tweetContent, new Date(), "kwetterJMS");
                    tweet.setOwner(username);
                    user.addTweet(tweet);

                    service.editUser(user);
                    System.out.println("----- tweet added by kwetterJMS -----");
                } catch (Exception ex) {

                }
            }
        } catch (JMSException jmsException) {
            Logger.getLogger(kwetterJMS.class.getName()).log(Level.SEVERE, null, jmsException);
        }
    }
}
