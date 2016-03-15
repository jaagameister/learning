package in.jaaga.learning.android.problems;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import in.jaaga.learning.ChatItem;
import in.jaaga.learning.Learning;
import in.jaaga.learning.Problem;
import in.jaaga.learning.R;
import in.jaaga.learning.Skill;
import in.jaaga.learning.android.S;

/**
 * Created by freeman on 2/3/16.
 */
public class PictureBook implements Skill {
    ArrayList<Integer> illustrations = new ArrayList<Integer>();
    String[] pageText;
    String[] books;
    int book = 0;
    int page = 0;

    public PictureBook() {
        books = S.getResources().getStringArray(R.array.books);
        for (int i = 0; i < books.length; i++)
            Log.d("load", "bookName: " + books[i]);
    }

    private void loadPages(String bookName) {
        System.out.println("load: "+bookName);
        int count = 1;
        System.out.println("picName: "+bookName+count);
        int resourceId = S.getResources().getIdentifier(bookName+count, "drawable", S.getActivity().getPackageName());
        Log.d("PictureBook", "resourceId " + resourceId);
        System.out.println("PictureBook + resourceId " + resourceId);
        while (resourceId > 0) {
            illustrations.add(new Integer(resourceId));
            String pageName = bookName + (++count);
            System.out.println("pageName: "+pageName);
            resourceId = S.getResources().getIdentifier(pageName, "drawable", S.getActivity().getPackageName());
            System.out.println("resourceId " + resourceId);
        }

        int textId = S.getResources().getIdentifier(bookName, "array", S.getActivity().getPackageName());
        if (textId > 0)
            pageText = S.getResources().getStringArray(textId);
    }

    public String getPrompt() {
//        return pages[page++];
        return "picture book";
    };

    public ChatItem getPromptChatItem() {
        boolean load = false;
        if (page >= illustrations.size()) {
            illustrations.clear();
            loadPages(books[(++book % books.length)]);
            page = 0;
        }

        String pText = null;
        if (pageText != null)
            pText = pageText[page];
        else
            pText = "";
        return new ChatItem(pText, illustrations.get(page++), Learning.TEXT_RESPONSE);
    }

    public void processResponse(String answer) {
        if ("end".equals(answer))
            page = illustrations.size() - 1;
    }

    public String getHint() {
        return "just hit enter for the next page";
    }
    public String getTitle() {
        return books[book];
    }

    public int getPoints() {
        return 0;
    }

    public void save(HashMap<String, String> session) {
        session.put("book", Integer.valueOf(book).toString());
        session.put("page", Integer.valueOf(page).toString());
    }

    public void restore(HashMap<String, String> session) {
        if (session.get("book") != null) {
            book = Integer.valueOf(session.get("book")).intValue();
            page = Integer.valueOf(session.get("page")).intValue();
        }
        loadPages(books[book]);
    }
}
