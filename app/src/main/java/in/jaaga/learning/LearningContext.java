package in.jaaga.learning;

/**
 * Created by freeman on 27/2/16.
 */
public class LearningContext {
    InteractionInterface interactionInterface;
    Session session;
    ChatBot chatBot;
    MissionLibrary missionLibrary;
    DB db;

    public LearningContext(InteractionInterface interactionInterface,
                   Session session,
                   ChatBot chatBot,
                   MissionLibrary missionLibrary,
                   DB db) {
        this.interactionInterface = interactionInterface;
        this.session = session;
        this.chatBot = chatBot;
        this.missionLibrary = missionLibrary;
        this.db = db;
    }

    public InteractionInterface getInteractionInterface() {
        return interactionInterface;
    }

    public Session getSession() {
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

}
