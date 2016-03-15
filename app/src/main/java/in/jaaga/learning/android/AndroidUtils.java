package in.jaaga.learning.android;

import android.app.Activity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Properties;

import in.jaaga.learning.InteractionInterface;
import in.jaaga.learning.Learning;
import in.jaaga.learning.LearningContext;
import in.jaaga.learning.MissionLibrary;

/**
 * Created by freeman on 8/3/16.
 */
public class AndroidUtils {
    public static Learning createLearning(Activity activity, InteractionInterface ix, Bundle bundle) {
        S.init(activity);
        HashMap<String, String> session = new HashMap<String, String>();
        if (bundle != null) {
            session = (HashMap<String, String>)bundle.getSerializable("session");
            System.out.println("session obj: "+session);
        }
//            session = (Properties)bundle.getSerializable("session");
        S.setSession(session);
        AndroidChatBot chatBot = new AndroidChatBot();
        MissionLibrary ml = new MissionLibrary();
        LearningContext ctx = new LearningContext(ix, session, chatBot, ml, new AndroidDB());
        ml.addMission("math", new AndroidMathMission(ctx));
        ml.addMission("vocab", new AndroidLanguageMission(ctx));
        Learning learning = new Learning(ctx);
        if (session.size() > 0)
            ctx.getMission().restore();
        return learning;
    }
/*
    public static void copyPropertiesToBundle(Properties properties, Bundle bundle) {
        if (properties == null || bundle == null)
            return;
        bundle.putString("mission", properties.getProperty("mission", "math"));
        bundle.putString("level", properties.getProperty("level", "0"));
        bundle.putString("answer", properties.getProperty("answer", "0"));
        bundle.putString("points", properties.getProperty("points", "0"));
    }

    public static void copyBundleToProperties(Bundle bundle, Properties properties) {
        if (properties == null || bundle == null)
            return;
        properties.setProperty("mission", bundle.getString("mission"));
        properties.setProperty("level", bundle.getString("level"));
        properties.setProperty("answer", bundle.getString("answer")); // save the current answer
        properties.setProperty("points", bundle.getString("points"));
    }
*/
}
