package in.jaaga.learning.cli;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

class Session {

    public static final String NAME = "name";
    public static final String ACTIVE = "active";

	Skill skill;
	String name;
    Activity activity;
    String state;

    public Session(Activity activity) {
        this.activity = activity;
        SharedPreferences sharedPrefs = activity.getPreferences(Context.MODE_PRIVATE);
        name = sharedPrefs.getString("name", "none");
        if ("none".equals(name))
            state = NAME;
        else
            state = ACTIVE;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Skill getSkill() {
		return skill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public void save() {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", getName());
        editor.commit();
    }
}