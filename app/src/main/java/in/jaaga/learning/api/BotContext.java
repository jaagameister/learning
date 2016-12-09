package in.jaaga.learning.api;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.R;

/**
 * Created by freeman on 19/4/16.
 */
public class BotContext {
    Activity activity;

    public BotContext() {}

    public BotContext(Activity activity) {
        this.activity = activity;
    }

    public Resources getResources() {
        return activity.getResources();
    }

    public SharedPreferences getSharedPreferences() {
        return activity.getPreferences(0);
    }
}
