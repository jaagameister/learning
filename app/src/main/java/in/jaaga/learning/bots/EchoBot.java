package in.jaaga.learning.bots;

import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;
//import android.R;

/**
 * Created by freeman on 19/4/16.
 */
public class EchoBot extends Bot {

    public void onMessageReceived(String text) {
        sender.send(new ChatItem(text, ChatItem.TEXT_RESPONSE));
    }
}
