package in.jaaga.learning.bots;


import android.os.AsyncTask;
import android.util.Log;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;

//Created by harish on 2/5/16.


public class TranslatorBot extends Bot {

    private String toLanguage;
    private String fromLanguage;

    public TranslatorBot() {}

    public TranslatorBot(String to) {
        this.toLanguage = to;
    }

    public TranslatorBot(String to,String from) {
        toLanguage = to;
        fromLanguage = from;
    }

    @Override
    public void onMessageReceived(String text) {
        super.onMessageReceived(text);
        if (needTranslation(text)) {
            // TODO: uncomment when added in fragment.
            translate(text);
        }
//        else if {
//            friendlyBot(text);
//        }
        else sender.send(new ChatItem("To translate add @language to it. For example:" +
                " @hindi What is your name?",ChatItem.TEXT_RESPONSE));
    }

    public void friendlyBot(String text) {

    }

    //translation api
    private boolean needTranslation(String originalText) {
        // @hindi
        if (originalText.startsWith("@")){
            return true;
        }
        return false;
    }

    private String getEligibleTranslatableString(String originalString) {
        String translatableString = originalString.split("@\\w+:")[1];
        translatableString.trim();
        return translatableString;
    }

    private String getLanguageFromString(String originalString) {
        int start = originalString.indexOf(":");
        String language = originalString.substring(1,start);
        return language.toUpperCase();
    }

    public void translate(String originalString) {
        sender.send(new ChatItem("Translating...",ChatItem.NO_RESPONSE));
        String language = getLanguageFromString(originalString);
        String eligibleString = getEligibleTranslatableString(originalString);

        String languageParam = "LANGUAGE." + language;

        String[] params = new String[]{eligibleString, languageParam};
        new TranslatorTask() {
            @Override
            protected void onPostExecute(String s) {
                sender.send(new ChatItem(s, ChatItem.TEXT_RESPONSE));
            }
        }.execute(params);
    }

    public class TranslatorTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String translated;
            String translatable = params[0];
            String language = params[1];
            Log.d("THIS IS LANGUAGE",language);
            Language to;
            if (language.equals("LANGUAGE.HINDI")) {
                to = Language.HINDI;
            } else if (language.equals("LANGUAGE.BULGARIAN")) {
                to = Language.BULGARIAN;
            } else if (language.equals("LANGUAGE.CATALAN")) {
                to = Language.CATALAN;
            } else if (language.equals("LANGUAGE.CHINESE")) {
                to = Language.CHINESE_SIMPLIFIED;
            } else if (language.equals("LANGUAGE.CZECH")) {
                to = Language.CZECH;
            } else if (language.equals("LANGUAGE.DANISH")) {
                to = Language.DANISH;
            } else if (language.equals("LANGUAGE.DUTCH")) {
                to = Language.DUTCH;
            } else if (language.equals("LANGUAGE.ENGLISH")) {
                to = Language.ENGLISH;
            } else if (language.equals("LANGUAGE.ESTONIAN")) {
                to = Language.ESTONIAN;
            } else if (language.equals("LANGUAGE.FINNISH")) {
                to = Language.FINNISH;
            } else if (language.equals("LANGUAGE.FRENCH")) {
                to = Language.FRENCH;
            } else if (language.equals("LANGUAGE.GERMAN")) {
                to = Language.GERMAN;
            } else if (language.equals("LANGUAGE.GREEK")) {
                to = Language.GREEK;
            }
            //HAITIAN_CREOLE NOT INCLUDED
            else if (language.equals("LANGUAGE.HEBREW")) {
                to = Language.HEBREW;
            } else if (language.equals("LANGUAGE.HMONG-DAW")) {
                to = Language.HMONG_DAW;
            } else if (language.equals("LANGUAGE.HUNGARIAN")) {
                to = Language.HUNGARIAN;
            } else if (language.equals("LANGUAGE.INDONESIAN")) {
                to = Language.INDONESIAN;
            } else if (language.equals("LANGUAGE.ITALIAN")) {
                to = Language.ITALIAN;
            } else if (language.equals("LANGUAGE.JAPANESE")) {
                to = Language.JAPANESE;
            } else if (language.equals("LANGUAGE.KOREAN")) {
                to = Language.KOREAN;
            } else if (language.equals("LANGUAGE.LATVIAN")) {
                to = Language.LATVIAN;
            } else if (language.equals("LANGUAGE.LITHUANIAN")) {
                to = Language.LITHUANIAN;
            } else if (language.equals("LANGUAGE.MALAY")) {
                to = Language.MALAY;
            } else if (language.equals("LANGUAGE.NORWEGIAN")) {
                to = Language.NORWEGIAN;
            } else if (language.equals("LANGUAGE.PERSIAN")) {
                to = Language.PERSIAN;
            } else if (language.equals("LANGUAGE.POLISH")) {
                to = Language.POLISH;
            } else if (language.equals("LANGUAGE.PORTUGUESE")) {
                to = Language.PORTUGUESE;
            } else if (language.equals("LANGUAGE.ROMANIAN")) {
                to = Language.ROMANIAN;
            } else if (language.equals("LANGUAGE.RUSSIAN")) {
                to = Language.RUSSIAN;
            } else if (language.equals("LANGUAGE.SLOVAK")) {
                to = Language.SLOVAK;
            } else if (language.equals("LANGUAGE.SLOVENIAN")) {
                to = Language.SLOVENIAN;
            } else if (language.equals("LANGUAGE.SPANISH")) {
                to = Language.SPANISH;
            } else if (language.equals("LANGUAGE.SWEDISH")) {
                to = Language.SWEDISH;
            } else if (language.equals("LANGUAGE.THAI")) {
                to = Language.THAI;
            } else if (language.equals("LANGUAGE.TURKISH")) {
                to = Language.TURKISH;
            } else if (language.equals("LANGUAGE.UKRAINIAN")) {
                to = Language.UKRAINIAN;
            } else if (language.equals("LANGUAGE.URDU")) {
                to = Language.URDU;
            } else if (language.equals("LANGUAGE.VIETNAMESE")) {
                to = Language.VIETNAMESE;
            } else {
                //DEFAULT HINDI
                to = Language.HINDI;
            } try {
                Translate.setClientId("Srini-The_Learning_App");
                Translate.setClientSecret("La2uB/ntI+r+gv1KDSq9vlPbCEiqfE+7Z2EK8e3+mEQ=");
                translated = Translate.execute(translatable, Language.ENGLISH,to);
            } catch (java.lang.Exception e) {
                translated = e.toString();
            } return translated;
        }
    }
}

