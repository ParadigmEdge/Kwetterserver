package kwetter.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import kwetter.service.KwetterService;

/**
 * @author RY Jin Async Message receiving bean.
 * mappedName should be the same as the queue name you made
 */
@MessageDriven(mappedName = "jms/myQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class SimpleMessageBean implements MessageListener {

    public SimpleMessageBean() {
    }
    
    @Inject 
    KwetterService ks;
    
    @Override // override the onMessage method 
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            System.out.println("----- MESSAGE RECEIVED -----");
            System.out.println(msg.getText());
            System.out.println("processing now...");
            
            if(processMessage(msg)){
                System.out.println("processing succeeded");
            } else {
                System.out.println("processing failed");
            }
        } catch (JMSException ex) {
            Logger.getLogger(SimpleMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean processMessage(TextMessage txt) {
        boolean passed = false;
        try {
            // get the string poperties that should be set by the messageprovider
            String username = txt.getStringProperty("username");
            String content = txt.getStringProperty("content");
            // create tweet will find the user using the username and add the tweet to the user
            // finally it calls the userDAO and (in this case) persists the user to the mysql database.
            ks.createTweet(content, username);
            passed = true;
        } catch (JMSException ex) {
            Logger.getLogger(SimpleMessageBean.class.getName()).log(Level.FINEST, null, ex);
        }
        return passed;
    }

}
