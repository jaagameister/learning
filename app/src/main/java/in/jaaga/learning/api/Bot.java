package in.jaaga.learning.api;

import android.os.Bundle;

/**
 * Created by freeman on 19/4/16.
 */
abstract public class Bot extends BotContext {
    public Sender sender;


    // Life cycle methods
    public void onCreate(Bundle savedInstanceState) {}

    public void onStart() {}

    public void onResume() {}

    public void onPause() {}

    public void onStop() {}

    public void onDestroy() {}


    // Communication methods
    public void onMessageReceived(String text) {}

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
