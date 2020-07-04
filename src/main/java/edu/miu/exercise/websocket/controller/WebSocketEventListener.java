package edu.miu.exercise.websocket.controller;

import edu.miu.exercise.websocket.model.ChatMessage;
import edu.miu.exercise.websocket.model.MessageType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger LOGGER= LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperation;

    @EventListener
    public void handleWebsocketConnectListener(final SessionConnectedEvent event){
        LOGGER.info("Wawoooo user has connected !");
    }

    @EventListener
    public void handleWebsocketDisconnectListener(final SessionDisconnectEvent event){
        final SimpMessageHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(event.getMessage());
        final String username= (String) headerAccessor.getSessionAttributes().get("username");
        final ChatMessage chatMessage=ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();
        sendingOperation.convertAndSend("/topic/public",chatMessage);

    }
}
