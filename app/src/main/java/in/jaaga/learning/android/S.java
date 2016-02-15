package in.jaaga.learning.android;

import android.app.Activity;
import android.content.res.Resources;
import android.content.SharedPreferences;
import android.content.Context;
/**
 * Created by freeman on 6/2/16.
 */
public class S {
    private static Resources resources;
    private static Activity activity = null;

    public static void init(Resources res, Activity act) {
        resources = res;
        activity = act;
    }
    public static SharedPreferences getPreferences() {
        return activity.getPreferences(0);
    }
    public static Resources getResources() {
        return  resources;
    }
    public static Activity getActivity() {
        return activity;
    }
}
