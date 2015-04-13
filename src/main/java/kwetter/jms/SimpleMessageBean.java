///**
// * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
// *
// * You may not modify, use, reproduce, or distribute this software except in
// * compliance with the terms of the License at:
// * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
// */
//package kwetter.jms;
//
//import javax.annotation.Resource;
//import javax.ejb.MessageDrivenContext;
//import kwetter.domain.Tweet;
//import kwetter.domain.User;
//import kwetter.service.KwetterService;
//import javax.ejb.MessageDriven;
//import javax.inject.Inject;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@MessageDriven
//public class SimpleMessageBean implements MessageListener {
//
//    @Resource
//    private MessageDrivenContext mdc;
//    static final Logger logger = Logger.getLogger("SimpleMessageBean");
//
//    @Inject
//    private KwetterService service;
//
//    public SimpleMessageBean() {
//    }
//
//    @Override
//    public void onMessage(Message message) {
//        System.out.println("----- ON MESSAGE CALLED -----");
//        try {
//            String username = message.getStringProperty("username");
//            String tweetContent = message.getStringProperty("content");
//
//            User user = service.findUserByName(username);
//            if (user != null) {
//                try {
//                    Tweet tweet = new Tweet(tweetContent, new Date(), "kwetterJMS");
//                    tweet.setOwner(username);
//                    user.addTweet(tweet);
//
//                    service.editUser(user);
//                    System.out.println("----- tweet added by kwetterJMS -----");
//                } catch (Exception ex) {
//
//                }
//            }
//        } catch (JMSException jmsException) {
//            Logger.getLogger(SimpleMessageBean.class.getName()).log(Level.SEVERE, null, jmsException);
//        }
//    }
//}
