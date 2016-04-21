package in.jaaga.learning.bots;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import in.jaaga.learning.api.ChatItem;
import in.jaaga.learning.R;
import in.jaaga.learning.api.Bot;

/**
 * Created by freeman on 2/3/16.
 */
public class PictureBook extends Bot {
    ArrayList<Integer> illustrations = new ArrayList<Integer>();
    String[] pageText;
    String[] books;
    int book = 0;
    int page = 0;

    public PictureBook() {
    }

    public void onStart() {
        books = getResources().getStringArray(R.array.books);
        for (int i = 0; i < books.length; i++)
            Log.d("load", "bookName: " + books[i]);
        sender.send(new ChatItem("Would you like a story about a cricket game that was won by a dog?"));
        loadPages(books[0]);
    }

    private void loadPages(String bookName) {
        System.out.println("load: "+bookName);
        int count = 1;
        System.out.println("picName: "+bookName+count);
        int resourceId = getResources().getIdentifier(bookName+count, "drawable",  getPackageName());
        Log.d("PictureBook", "resourceId " + resourceId);
        System.out.println("PictureBook + resourceId " + resourceId);
        while (resourceId > 0) {
            illustrations.add(new Integer(resourceId));
            String pageName = bookName + (++count);
            System.out.println("pageName: "+pageName);
            resourceId = getResources().getIdentifier(pageName, "drawable", getPackageName());
            System.out.println("resourceId " + resourceId);
        }

        int textId = getResources().getIdentifier(bookName, "array", getPackageName());
        if (textId > 0)
            pageText = getResources().getStringArray(textId);
    }

    public String getPrompt() {
//        return pages[page++];
        return "picture book";
    };

    public void onMessageReceived(String text) {
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
        sender.send(new ChatItem(pText, illustrations.get(page++), ChatItem.TEXT_RESPONSE));
    }
}
