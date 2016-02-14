package in.jaaga.learning;

import android.content.SharedPreferences;

import in.jaaga.learning.android.S;

/**
 * Created by root on 14/2/16.
 */
public class DB {

    public static void addName(String name) {
        SharedPreferences pref = S.getPreferences();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, "password");
        editor.commit();
    }

    public static boolean containsName(String name) {
        SharedPreferences pref = S.getPreferences();
        return pref.contains(name);
    }
}