package in.jaaga.learning.api;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * Created by freeman on 19/4/16.
 */
abstract public class Bot {
    public Sender sender;
    public Context context;

    // Life cycle methods
    public void onCreate(Bundle savedInstanceState) {}

    public void onAttach(Context context) {
        this.context = context;
    }

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


    // Utility methods
    public Resources getResources() {
        return context.getResources();
    }

    public String getPackageName() { return context.getPackageName(); }

}
