package in.jaaga.learning.bots;

import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;

import java.util.*;
/*
 * Language chat-bot using simple rules.
 * The chat-bot follows the flow defined in the "bot_hello.xml" file.
 * Anuj Khare <khareanuj18@gmail.com>
 * 17 Nov '16'
 */

public class Anuj extends Bot {
    String curBot;  // The conversation being used right now
    String curQId;  // the current question ID (NOT question string)
    String [] cur_replies;  // list of currently allowed replies

    /* Getter for curQId */
    public String get_curQId() {
        return this.curQId;
    }

    /* Getter for cur_replies */
    public String [] get_cur_replies() {
        return this.cur_replies;
    }


    private String get_string_from_name(String name) {
      int id = getResources().getIdentifier(name, "string",
                                            getPackageName());
      if (id == 0) {  // TODO: Handle notfounds gracefully
        System.out.println("NOT FOUND!");
        return "";
      }
      
      return getResources().getString(id);
    }

    private String [] get_string_array_from_name(String name) {
      System.out.println("THE NAME: " + name);
      int id = getResources().getIdentifier(name, "array",
                                            getPackageName());
      if (id == 0) {  // TODO: Handle notfounds gracefully
        System.out.println("NOT FOUND!");
        return new String[0];
      }
      System.out.println(getResources().getStringArray(id).toString());
      return getResources().getStringArray(id);
    }

    /* the list of reply blobs a question has */
    private String get_reply_blobs_id(String ques_id) {
      return (ques_id + "_reply_blobs");
    }

    /* Get the list of reply_blobs for a given question */
    private String [] get_reply_blobs(String ques_id) {
      return get_string_array_from_name(get_reply_blobs_id(ques_id));
    }

    private String [] get_reply_list_from_name(String reply_name) {
      return get_string_array_from_name(reply_name + "_list");
    }

    /* Get all the possible replies for a given question */
    private String [] get_all_replies(String ques_id) {
       // get the "categories" / blobs for possible replies
       String [] reply_blobs = get_reply_blobs(ques_id);
       List<String> replies = new ArrayList<String>();
       // get all possible replies from within the blobs
       for (int i=0; i<reply_blobs.length; ++i) {
         replies.addAll(Arrays.asList(get_reply_list_from_name(reply_blobs[i])));
       }
       return replies.toArray(new String[0]);
    }

    @Override
    public void onStart() {
        super.onStart();
        curBot = "hellobot";

        curQId = curBot + "_start";
        System.out.println(this.cur_replies == null);
        System.out.println(this.cur_replies);
 
        // TODO: Figure out if we need to free this array first. Memory leaks?
        this.cur_replies = get_all_replies(this.curQId);

        ChatItem item = new ChatItem(get_string_from_name(this.curQId), ChatItem.TEXT_RESPONSE);
        item.setResponseOptions(this.cur_replies);
        sender.send(item);

        // curQId = getResources().getIdentifier("_start_", "string", getPackageName());
        // System.out.println(curQIdId);

        // showPrompt();
    }

    // public void showPrompt() {
    //     // get sentence
    //     int sId = getResources().getIdentifier("verb" + count, "string", getPackageName());
    //     System.out.println(sId);
    //     if (sId == 0) {
    //         count = 1;
    //         sId = getResources().getIdentifier("verb" + count, "string", getPackageName());
    //     }
    //     System.out.println(count);
    //     String sentence = getResources().getString(sId);

    //     // get options
    //     int optionId = getResources().getIdentifier("verb" + count, "array", getPackageName());
    //     String[] options = getResources().getStringArray(optionId);
    //     answer = options[0];
    //     ChatItem item = new ChatItem(sentence, ChatItem.TEXT_RESPONSE);
    //     item.setResponseOptions(options);
    //     sender.send(item);
    // }

    // @Override
    // public void onMessageReceived(String text) {
    //     if (answer.equalsIgnoreCase(text)) {
    //         sender.send(new ChatItem("Awesome", ChatItem.NO_RESPONSE));
    //         count++;
    //         showPrompt();
    //         return;
    //     }
    //     sender.send(new ChatItem("Nope. Try again", ChatItem.NO_RESPONSE));
    //     showPrompt();
    // }
};
