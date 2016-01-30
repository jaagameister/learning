package in.jaaga.learning.pojo;

/**
 * Created by root on 24/1/16.
 */
public class ChatItem {
    String message;
    int responseType;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    String sender;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResponseType(int type) {
        responseType = type;
    }

    public int getResponseType() {
        return responseType;
    }
}
