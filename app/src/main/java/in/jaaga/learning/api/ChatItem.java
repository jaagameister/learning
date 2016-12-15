package in.jaaga.learning.api;

import java.io.Serializable;
import java.util.ArrayList;

import in.jaaga.learning.api.*;

/**
 * Created by root on 24/1/16.
 */
public class ChatItem implements Serializable {
    public static final int NO_RESPONSE = 1;
    public static final int NUMBER_RESPONSE = 2;
    public static final int TEXT_RESPONSE = 3;
    public static final String BOT = "bot";

    String sender;
    String message;
    int attachment;
    int resourceId = -1;
    int responseType;

    public ChatItem() {}

    public ChatItem(String message) {
        this.message = message;
        sender = BOT;
        responseType = TEXT_RESPONSE;
    }

    public ChatItem(String message, String[] responseOptions) {
        this(message);
        setResponseOptions(ChatItem.convertToChatReplyOptions(responseOptions));
    }

    public ChatItem(String message, ChatReply[] responseOptions) {
        this(message);
        setResponseOptions(responseOptions);
    }

    public ChatItem(String message, int responseType) {
        this(message);
        this.responseType = responseType;
    }

    public ChatItem(String message, int resourceId, int responseType) {
        this(message, responseType);
        this.resourceId = resourceId;
    }

    public ChatItem(String message, int resourceId, String[] responseOptions) {
        this(message, resourceId, ChatItem.convertToChatReplyOptions(responseOptions));
    }

    public ChatItem(String message, int resourceId, ChatReply[] responseOptions) {
        this(message, resourceId, NO_RESPONSE);
        setResponseOptions(responseOptions);
    }

    public ChatItem(String message, int responseType, String sender) {
        this(message, responseType);
        this.sender = sender;
    }

    // TODO: use these options to create buttons for custom keyboard
    ChatReply[] options;
    public void setResponseOptions(ChatReply[] options) {
        this.options = options;
    }

    public ChatReply[] getResponseOptions() {
        return options;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResourceId(int resourceId ) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResponseType(int type) {
        responseType = type;
    }

    public int getResponseType() {
        return responseType;
    }

    private static ChatReply[] convertToChatReplyOptions(String[] options) {
        ArrayList<ChatReply> replies = new ArrayList<>(options.length);
        ChatReply reply;
        for (int i = 0; i < options.length; i++){
            reply = new ChatReply();
            reply.type = ChatReplyType.Regular;
            reply.displayText = options[i];
            replies.add(reply);
        }
        return replies.toArray(new ChatReply[0]);
    }
}
