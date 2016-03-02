package in.jaaga.learning;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by freeman on 27/2/16.
 */
public class MissionLibrary {
    HashMap<String, Mission> missions = new HashMap();
    Mission defaultMission;

    public Mission getDefaultMission() {
        return defaultMission;
    }

    public void setDefaultMission(Mission mission) {
        this.defaultMission = mission;
    }

    public void addMission(String descriptor, Mission mission) {
        if (defaultMission == null)
            defaultMission = mission;
        missions.put(descriptor, mission);
    }

    public Mission getMission(String descriptor) {
        return missions.get(descriptor.trim());
    }

    public Collection<Mission> getAvailableMissions() {
        return missions.values();
    }

    public String getAvailableMissionsHelpStatement() {
        StringBuffer sb = new StringBuffer();
        sb.append("available missions are: \n");
        for (String key : missions.keySet()) {
            sb.append(key + " - "+missions.get(key).getTitle()+"\n");
        }
        return sb.toString();
    }
}
