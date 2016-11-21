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
    String [] curReplies;  // list of currently allowed replies
    String [] curReplyBlobs;
    String end_id;

    /* Getter for curQId */
    public String get_curQId() {
        return this.curQId;
    }

    /* Getter for curReplies */
    public String [] getCurReplies() {
        return this.curReplies;
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
    private void set_cur_replies() {
       List<String> replies = new ArrayList<String>();
       // TODO: do it better!
       List<String> curReplyBlobs = new ArrayList<String>();

       // get the "categories" / blobs for possible replies
       String [] reply_blobs = get_reply_blobs(this.curQId);
       // get all possible replies from within the blobs
       for (int i=0; i<reply_blobs.length; ++i) {
         String [] reply_list = get_reply_list_from_name(reply_blobs[i]);
         replies.addAll(Arrays.asList(reply_list));
         for(int j=0; j<reply_list.length; ++j) {
            curReplyBlobs.add(reply_blobs[i]);
         }
       }
       this.curReplies = replies.toArray(new String[0]);
       this.curReplyBlobs = curReplyBlobs.toArray(new String[0]);
       System.out.println(Arrays.toString(this.curReplies));
       System.out.println(Arrays.toString(this.curReplyBlobs));
    }

    @Override
    public void onStart() {
        super.onStart();
        this.curBot = "hellobot";

        // The first thing the bot says is always in "<bot-name>_start"
        this.curQId = this.curBot + "_start";
        // System.out.println(this.curReplies == null);
        // System.out.println(this.curReplies);

        // the id of the last thing is always "<bot-name>_end";
        this.end_id = this.curBot + "_end";
 
        showNextQuestion();
    }

    @Override
    public void onMessageReceived(String text) {
        int replyIndex = Arrays.asList(this.curReplies).indexOf(text);
        if(replyIndex == -1) {
          System.out.println("INVALID REPLY RECEIVED!");
          return;
        }

        String curReplyBlob = this.curReplyBlobs[replyIndex];
        String [] nextQIds = get_string_array_from_name(curReplyBlob + "_nextQId");

        // NOTE: If there are multiple nextQIds, only the last one actually has
        // replies, others are just things that the bot says before the
        // question.
        for(int i=0; i<nextQIds.length; ++i) {
          this.curQId = nextQIds[i];
          showNextQuestion();
        }
        // TODO: HANDLE THE END!!
    }


     private void showNextQuestion() {
         // TODO: Figure out if we need to free this array first. Memory leaks?
         // this.curReplies = get_all_replies(this.curQId);
         set_cur_replies();

         ChatItem item = new ChatItem(get_string_from_name(this.curQId), ChatItem.TEXT_RESPONSE);
         item.setResponseOptions(this.curReplies);
         sender.send(item);
     }
};
