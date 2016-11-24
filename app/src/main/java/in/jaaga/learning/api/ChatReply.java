package in.jaaga.learning.api;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by dharmesh on 24/11/16.
 */

public class ChatReply {
    public ChatReplyType type;
    public String formatString;
    public String displayText;
    public ArrayList<String> parameterNames;
    public Hashtable<String, String> parameters;
    public String replyText;
}
