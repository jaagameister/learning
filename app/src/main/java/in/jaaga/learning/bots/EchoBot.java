package in.jaaga.learning.bots;

import android.app.Activity;

import in.jaaga.learning.R;
import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;
import in.jaaga.learning.bots.srini.android.AndroidChatBot;
//import android.R;

/**
 * Created by freeman on 19/4/16.
 */
public class EchoBot extends Bot {

    public void onMessageReceived(String text) {
        sender.send(new ChatItem(text, ChatItem.TEXT_RESPONSE));
    }
}
