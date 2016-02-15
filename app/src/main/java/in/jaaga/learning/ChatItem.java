package in.jaaga.learning;

/**
 * Created by root on 24/1/16.
 */
public class ChatItem {
    public static final int NO_RESPONSE = 0;
    public static final int NUMBER_RESPONSE = 1;
    public static final int TEXT_RESPONSE = 2;

    String sender;
    String message;
    String attachment;
    int resourceId = -1;
    int responseType;

    public ChatItem() {}

    public ChatItem(String message) {
        this.message = message;
        sender = "sender";
        responseType = NO_RESPONSE;
    }

    public ChatItem(String message, int responseType) {
        this(message);
        this.responseType = responseType;
    }

    public ChatItem(String message, String attachment, int responseType) {
        this(message, responseType);
        this.attachment = attachment;
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
}
