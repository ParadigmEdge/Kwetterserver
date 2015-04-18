package kwetter.websocket;

import kwetter.service.KwetterService;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Yongyi Jin
 */
@ServerEndpoint("/demoSocket")
public class DemoEndpoint {
    
    @Inject
    private KwetterService service;

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        System.out.println("session ID: "+session.getId());
        System.out.println("Connection to endpoint opened...");
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println(session);
        System.out.println("Message received on demo endpoint...");
        System.out.println("Received message: " + msg);
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.println("Connection to endpoint closed...");
    }
}
