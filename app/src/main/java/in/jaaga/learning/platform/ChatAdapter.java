package in.jaaga.learning.platform;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.jaaga.learning.R;
import in.jaaga.learning.api.ChatItem;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ChatItem> chat_list;
    private Context ctx;
    private static final int VIEW_TYPE_LEFT = 0, VIEW_TYPE_RIGHT = 1;
    private static final String CHAT_BOT = ChatItem.BOT;


    public ChatAdapter(Context ctx,ArrayList<ChatItem> chat_list){

        this.ctx = ctx;
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
            mHolder.attachment.setImageResource(resourceId);
            mHolder.attachment.setVisibility(View.VISIBLE);
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
        }
    }
}
