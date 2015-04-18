package kwetter.websocket;

import kwetter.service.KwetterService;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Yongyi Jin
 */
@ServerEndpoint("/websocket")
public class KwetterEndpoint {

    @Inject
    private KwetterService service;

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        System.out.println("Connection to endpoint opened...");
        //TODO
//        String username = session.getUserPrincipal().getName();
//        service.setSession(username, session);
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("Message received on kwetter endpoint...");
        System.out.println("Received message: " + msg);
        //TODO
//        String username = session.getUserPrincipal().getName();
//        String tweetContent = msg;
//        service.createTweet(tweetContent, username);
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.println("Connection to endpoint closed...");
        //TODO
//        String username = session.getUserPrincipal().getName();
//        service.removeSession(username);
    }
}
