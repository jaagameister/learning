package in.jaaga.learning;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created by freeman on 27/2/16.
 */

// TODO: make sure MissionLibrary gets initialized before we start
public class LearningContext {
    InteractionInterface ix;
    HashMap<String, String> session;
    ChatBot chatBot;
    MissionLibrary missionLibrary;
    DB db;

    Mission mission;

    public LearningContext(InteractionInterface interactionInterface,
                           HashMap session,
                   ChatBot chatBot,
                   MissionLibrary missionLibrary,
                   DB db) {
        this.ix = interactionInterface;
        this.session = session;
        this.chatBot = chatBot;
        this.missionLibrary = missionLibrary;
        this.db = db;
    }

    public InteractionInterface getInteractionInterface() {
        return ix;
    }

    public HashMap<String, String> getSession() {
        return session;
    }

    public ChatBot getChatBot() {
        return chatBot;
    }

    public MissionLibrary getMissionLibrary() {
        return missionLibrary;
    }

    public DB getDB() {
        return db;
    }

    public Mission getMission() {
        if (mission == null) {
            String savedMissionName = session.get("mission");
            if (savedMissionName != null) {
                setMission(missionLibrary.getMission(savedMissionName));
            }
        }

        if (mission == null) {
            setMission(missionLibrary.getDefaultMission());
        }
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

}
