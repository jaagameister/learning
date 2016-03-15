/*package in.jaaga.learning.cli;

import java.util.Scanner;

import in.jaaga.learning.*;
import in.jaaga.learning.missions.Easy;
import in.jaaga.learning.missions.MathMission;
import in.jaaga.learning.missions.NegativeNumbers;


public class CLI implements InteractionInterface {
    Learning learning;

    public static void main(String[] argv) {
        new CLI();
    }

    public CLI() {
        Session.setDevice("CLI");
        Session session = new Session();

        MissionLibrary ml = new MissionLibrary();
        ml.addMission("math", new MathMission());
        ml.addMission("easy", new Easy());
        ml.addMission("negative", new NegativeNumbers());

        LearningContext learningContext = new LearningContext(this, session, new ChatBot(session),
                ml, new DB());
        learning = new Learning(learningContext);
        learning.start();
    }

    public void send(ChatItem item) {
        System.out.println("srinivas: "+item.getMessage());
        if (item.getResponseType() != Learning.NO_RESPONSE) {
            if (!item.getMessage().contains("Congratulations")) {
                System.out.print("you: ");
                Scanner sc = new Scanner(System.in);
                learning.onResponse(new Scanner(System.in).nextLine());
            }
        }
    }
}
*/