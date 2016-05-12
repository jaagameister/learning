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
    public static final String[] OPTIONS = {"back", "next"};
    int book = 2;
    int page = 0;

    public PictureBook() {
    }

    public void onStart() {
        books = getResources().getStringArray(R.array.books);
        for (int i = 0; i < books.length; i++) {
            Log.d("load", "bookName: " + books[i]);
        }
        loadPages(books[book]);
        onMessageReceived("");
//        sender.send(new ChatItem("Would you like a story about a cricket game that was won by a dog?"));
    }

    private void loadPages(String bookName) {
        System.out.println("load: " + bookName);
        int count = 1;
        System.out.println("picName: " + bookName + count);

        int resourceId = getResources().getIdentifier(bookName + count, "drawable", getPackageName());
        Log.d("PictureBook", "resourceId " + resourceId);
        System.out.println("PictureBook + resourceId " + resourceId);
        while (resourceId > 0) {
            illustrations.add(new Integer(resourceId));
            String pageName = bookName + (++count);
            System.out.println("pageName: " + pageName);
            resourceId = getResources().getIdentifier(pageName, "drawable", getPackageName());
            System.out.println("resourceId " + resourceId);
        }

        int textId = getResources().getIdentifier(bookName, "array", getPackageName());
        if (textId > 0) {
            pageText = getResources().getStringArray(textId);
            Log.d("PictureBook", "pageText " + pageText[0]);
        } else {
            pageText = null;
        }
    }

    public void onMessageReceived(String text) {
        Log.d("onMessageReceived: text", text);
        Log.d("onMessageReceived: page", ""+page);
        String pText = null;
        int illustration;

        if ("next".equalsIgnoreCase(text)) {
            if (++page >= illustrations.size()) {
                Log.d("onMessageReceived", "page >= illustrations.size");
                illustrations.clear();
                loadPages(books[(++book % books.length)]);
                page = 0;
            }

        } else { // back
            if (--page <= 0) {
                Log.d("onMessageReceived", "page = 0 & action = back");
                illustrations.clear();
                book = ((book - 1 + books.length) % books.length);
                loadPages(books[book]);
                page = books[book].length();
            }
        }

        if (pageText != null && pageText.length > page)
            pText = pageText[page];
        else
            pText = "";
        sender.send(new ChatItem(pText, illustrations.get(page), OPTIONS));
    }
}
