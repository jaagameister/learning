package in.jaaga.learning.t2s;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Rahul Kumar on 10/16/2016.
 */

public class Speech implements TextToSpeech.OnInitListener {
    public TextToSpeech textToSpeech;
    private boolean allowed = true, ready = false;

    public Speech(Context context){
        textToSpeech = new TextToSpeech(context, this);
    }
    public void speak(String text){
        if(ready && allowed) {
            HashMap<String, String> hash = new HashMap<String,String>();
            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                    String.valueOf(AudioManager.STREAM_NOTIFICATION));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            } else textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    @Override
    public void onInit(int i) {
        if(i==TextToSpeech.SUCCESS){
            textToSpeech.setLanguage(new Locale("hi", "IN"));
            ready = true;
        }else ready = false;
    }

    public boolean isAllowed(){
        return allowed;
    }
    public void allow(boolean allowed){
        this.allowed = allowed;
    }

    public boolean isReady(){
        return ready;
    }
    public void setReady(boolean ready){
        this.ready = ready;
    }

    public void destroy(){
        textToSpeech.shutdown();
    }
}
