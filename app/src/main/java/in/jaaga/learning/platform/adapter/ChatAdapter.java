package in.jaaga.learning.platform.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.jaaga.learning.R;
import in.jaaga.learning.api.ChatItem;

import static in.jaaga.learning.platform.MainActivity.myDbHelper;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ChatItem> chat_list;
    private static Context mContext;
    private static final int VIEW_TYPE_LEFT = 0, VIEW_TYPE_RIGHT = 1;
    private static final String CHAT_BOT = ChatItem.BOT;
    private AlertDialog.Builder alertDialogBuilder;

    public ChatAdapter(Context myContext, ArrayList<ChatItem> chat_list){

        this.mContext = myContext;
        this.chat_list = chat_list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View v;

        if(viewType==VIEW_TYPE_LEFT) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_msg_left, parent, false);
        }
        else{
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_msg_right, parent, false);
        }

        viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatItem ci = chat_list.get(position);
        String message = ci.getMessage();
        int resourceId = ci.getResourceId();

        ViewHolder mHolder = (ViewHolder) holder;
        mHolder.message.setText(message);

        System.out.println("resourceId:"+resourceId);
        if (resourceId != -1) {
            mHolder.attachment.setAdjustViewBounds(true);
            mHolder.attachment.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(resourceId).into(mHolder.attachment);
        } else {
            mHolder.attachment.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return chat_list.get(position).getSender().equals(CHAT_BOT) ? VIEW_TYPE_LEFT : VIEW_TYPE_RIGHT;
    }

    @Override
    public int getItemCount() {
        return chat_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView message;
        public ImageView attachment;

        public ViewHolder(View vi) {
            super(vi);
            message = (TextView) vi.findViewById(R.id.chat_txt);
            attachment = (ImageView) vi.findViewById(R.id.image_holder);
            message.setCustomSelectionActionModeCallback(new android.view.ActionMode.Callback() {
                final static int DEFINITION = 0;
                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    menu.removeItem(android.R.id.selectAll);
                    menu.removeItem(android.R.id.cut);
                    return true;
                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    menu.add(0, DEFINITION, 0, "Definition").setIcon(R.drawable.dictionary);
                    return true;
                }


                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    // Called when an action mode is about to be exited and
                    // destroyed
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case DEFINITION:
                            int min = 0;
                            int max = message.getText().length();
                            if (message.isFocused()) {
                                int selStart = message.getSelectionStart();
                                int selEnd = message.getSelectionEnd();

                                min = Math.max(0, Math.min(selStart, selEnd));
                                max = Math.max(0, Math.max(selStart, selEnd));
                            }
                            // Perform your definition lookup with the selected text
                            String selectedText = message.getText().subSequence(min, max).toString();
                            String[] wordList = selectedText.split("[ ,.]");
                            if(wordList.length == 1){
                                showDefinitionDialog(wordList[0]);
                            }
                            else{
                                showWordListDialog(wordList);
                            }
                            mode.finish();
                            return true;
                        default:
                            break;
                    }

                    return false;
                }

            });
        }

        private void showDefinitionDialog(final String word) {
            myDbHelper.openDataBase();
            final Cursor cursor = myDbHelper.getResults(word);
            CustomDialogClass cdd = new CustomDialogClass(mContext);
            if (cursor != null){
                cdd.setValues(cursor.getString(0).trim(), " - " + cursor.getString(1).trim(), cursor.getString(2).trim());
                cursor.close();
            }
            else cdd.setValues(word, "", "Nothing Found!");
            cdd.show();

        }

        private void showWordListDialog(final String[] wordList){
            if(wordList.length != 0){
                alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setTitle("Select one word");
                alertDialogBuilder.setItems(wordList, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showDefinitionDialog(wordList[i]);
                        dialogInterface.dismiss();
                    }
                });
            }else{
                alertDialogBuilder.setTitle("Nothing Found");
            }
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
