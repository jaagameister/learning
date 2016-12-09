package in.jaaga.learning.bots.skillbot.problems;

import java.util.List;

import in.jaaga.learning.api.ChatItem;
import in.jaaga.learning.bots.skillbot.Problem;

/**
 * Created by freeman on 29/4/16.
 */
/*
public class Sentence {
 String prompt;
        List<String> options;
        int current = 1;
        int total;

        public Sentence() {
            int i = 1;
            int resourceId = getResources().getIdentifier("sentence" + (i++), "string", S.getActivity().getPackageName());
            while (resourceId > 0) {
                resourceId = S.getResources().getIdentifier("sentence"+(i++), "string", S.getActivity().getPackageName());
            }
            total = i - 2;
            System.out.println("sentence total: "+total);
        }

        public String getPrompt() {
            int sentenceId = S.getResources().getIdentifier("sentence" + current, "string", S.getActivity().getPackageName());
            String sentence = S.getResources().getString(sentenceId);

            int optionsId = S.getActivity().getResources().getIdentifier("sentence" + current + "_options", "array", S.getActivity().getPackageName());
            options = Arrays.asList(S.getResources().getStringArray(optionsId));

            StringBuffer sb = new StringBuffer(sentence + "\n(");
            for (int i = 0; i < options.size(); i++) {
                sb.append(options.get(i));
                if (i < options.size() - 1)
                    sb.append(", ");
            }
            sb.append(')');
            return sb.toString();
        }

        public ChatItem getPromptChatItem() {
            return new ChatItem(getPrompt());
        }
}

}
*/