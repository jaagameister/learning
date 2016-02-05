package in.jaaga.learning.missions;

import java.util.ArrayList;
import in.jaaga.learning.Skill;

public interface Mission {
    public ArrayList<Skill> getList();
    public String getTitle();
}