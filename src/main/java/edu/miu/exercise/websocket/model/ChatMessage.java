package edu.miu.exercise.websocket.model;

import lombok.Builder;
import lombok.Getter;

import edu.miu.exercise.websocket.model.MessageType;

@Builder
public class ChatMessage {
    @Getter
    private MessageType type;
    @Getter
    private String content;
    @Getter
    private String sender;
    @Getter
    private String time;

}
