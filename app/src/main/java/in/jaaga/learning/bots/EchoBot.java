package in.jaaga.learning.bots;

import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;
//import android.R;

/**
 * Created by freeman on 19/4/16.
 */
public class EchoBot extends Bot {

    @Override
    public void onStart() {
        sender.send(new ChatItem("I'm rubber and you're glue. Everything you say bounces off me and sticks to you",
                ChatItem.TEXT_RESPONSE));
    }

    public void onMessageReceived(String text) {
        sender.send(new ChatItem(text, ChatItem.TEXT_RESPONSE));
    }
}
