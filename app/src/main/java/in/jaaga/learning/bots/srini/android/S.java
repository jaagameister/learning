package in.jaaga.learning.bots.srini.android;

import android.app.Activity;
import android.content.res.Resources;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by freeman on 6/2/16.
 */
public class S {
    private static Resources resources;
    private static Activity activity = null;
    private static HashMap session;

    public static void init(Activity act) {
        resources = act.getResources();
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
    public static HashMap getSession() {
        return session;
    }
    public static void setSession(HashMap _session) {
        session = _session;
    }
}
