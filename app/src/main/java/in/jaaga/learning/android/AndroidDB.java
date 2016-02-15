package in.jaaga.learning.android;

import android.content.SharedPreferences;

import in.jaaga.learning.DB;

public class AndroidDB extends DB {

    public void addName(String name) {
        SharedPreferences pref = S.getPreferences();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, "password");
        editor.commit();
    }

    public boolean containsName(String name) {
        SharedPreferences pref = S.getPreferences();
        return pref.contains(name);
    }
}