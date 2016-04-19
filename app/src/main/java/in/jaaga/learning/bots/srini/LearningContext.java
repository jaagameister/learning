package in.jaaga.learning.bots.srini;

import java.util.HashMap;

import in.jaaga.learning.api.Sender;

/**
 * Created by freeman on 27/2/16.
 */

// TODO: make sure MissionLibrary gets initialized before we start
public class LearningContext {
    Sender ix;
    HashMap<String, String> session;
    ChatBot chatBot;
    MissionLibrary missionLibrary;

    Mission mission;

    public LearningContext(Sender interactionInterface,
                           HashMap session,
                   ChatBot chatBot,
                   MissionLibrary missionLibrary) {
        this.ix = interactionInterface;
        this.session = session;
        this.chatBot = chatBot;
        this.missionLibrary = missionLibrary;
    }

    public Sender getInteractionInterface() {
        return ix;
    }

    public HashMap<String, String> getSession() {
        if (session == null)
            session = new HashMap<String, String>();
        return session;
    }

    public ChatBot getChatBot() {
        return chatBot;
    }

    public MissionLibrary getMissionLibrary() {
        return missionLibrary;
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
