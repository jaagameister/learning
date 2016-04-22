package in.jaaga.learning.api;

import java.io.Serializable;

/**
 * Created by root on 24/1/16.
 */
public class ChatItem implements Serializable {
    public static final int NO_RESPONSE = 0;
    public static final int NUMBER_RESPONSE = 1;
    public static final int TEXT_RESPONSE = 2;
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
        responseType = NO_RESPONSE;
    }

    public ChatItem(String message, int responseType) {
        this(message);
        this.responseType = responseType;
    }

    public ChatItem(String message, int resourceId, int responseType) {
        this(message, responseType);
        this.resourceId = resourceId;
    }

    public ChatItem(String message, int responseType, String sender) {
        this(message, responseType);
        this.sender = sender;
    }

    // TODO: use these options to create buttons for custom keyboard
    public void setResponseOptions(String[] options) {}

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
}
