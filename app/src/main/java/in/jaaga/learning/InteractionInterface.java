package in.jaaga.learning;

import android.app.Activity;

/**
 * Created by root on 24/1/16.
 */
public interface InteractionInterface {
    Activity getActivity();
    void Send(ChatItem item);
}
