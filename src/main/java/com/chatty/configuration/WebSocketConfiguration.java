package com.chatty.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // enable web-socket server
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    //  STOMP stands for Simple Text Oriented Messaging Protocol.
    //  It is a messaging protocol that defines the format and rules for data exchange.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
        // SockJS is used to enable fallback options for browsers that don’t support websocket.
    }

    //  we’re configuring a message broker that will be used to route messages from one client to another.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // the messages whose destination starts with “/app” should be routed to message-handling methods
        registry.setApplicationDestinationPrefixes("/app");

        // the messages whose destination starts with “/topic” should be routed to the message broker.
        // Message broker broadcasts messages to all the connected clients who are subscribed to a particular topic.
        registry.enableSimpleBroker("/topic");
    }
}