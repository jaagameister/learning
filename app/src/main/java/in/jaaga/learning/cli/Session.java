package in.jaaga.learning.cli;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

class Session {
	Skill skill;
    SharedPreferences profile;
    Activity activity;
    String state;
    String name;
    int points = 0;

    public Session(Activity activity) {
        this.activity = activity;
        profile = activity.getPreferences(Context.MODE_PRIVATE);
        name = profile.getString("name", null);
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

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void save() {
        if (name != null) {
            SharedPreferences.Editor editor = profile.edit();
            editor.putString("name", name);
            editor.apply();
        }
    }
}