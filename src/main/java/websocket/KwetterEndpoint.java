package websocket;

import domain.User;
import service.KwetterService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by Yongyi Jin
 */
@ServerEndpoint("/websocket")
public class KwetterEndpoint
{
    @Inject
    private KwetterService service;

    @OnMessage
    public void onMessage(Session session, String msg){

        String username = session.getUserPrincipal().getName();
        String tweetContent = msg;
        User user = service.findUserByName(username);
//        Tweet tweet = new Tweet(user, tweetContent, new Date(), "Kwetter push!");
//        service.postNewTweet(tweet);
    }

    @OnOpen
    public void open(Session session, EndpointConfig conf){
        String username = session.getUserPrincipal().getName();

        //Allow batch processing for incredibly famous kwetter pppl
        try
        {
            session.getAsyncRemote().setBatchingAllowed(true);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //service.setSession(username, session);
    }

    @OnClose
    public void close(Session session, CloseReason reason){
            String username = session.getUserPrincipal().getName();
            //service.removeSession(username);
    }
}
