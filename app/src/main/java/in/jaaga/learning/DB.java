package in.jaaga.learning;

import java.util.HashMap;

/**
 * Created by root on 14/2/16.
 */
public class DB {
    HashMap<String, String> db = new HashMap();

    public void addName(String name) {
        db.put(name, "password");
    }

    public boolean containsName(String name) {
        return db.containsKey(name);
    }
}