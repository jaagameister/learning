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

    public final void onAttach(Context context) {
        this.context = context;
    }

    public void onStart() {}

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
