package in.jaaga.learning.platform.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import in.jaaga.learning.R;
import in.jaaga.learning.platform.MainActivity;
import in.jaaga.learning.platform.util.CircleImageView;

public class BotListAdapter extends RecyclerView.Adapter<BotListAdapter.ViewHolder> {

    private ArrayList<HashMap<String, String>> bot_list = new ArrayList<HashMap<String, String>>();
    OnItemClickListener itemClickListener;
    private int layoutResourceIds;
    private Context mContext;


    public BotListAdapter(Context mContext, int layoutResourceId, ArrayList<HashMap<String, String>> bot_list, OnItemClickListener itemClickListener) {

        this.itemClickListener = itemClickListener;
        this.layoutResourceIds = layoutResourceId;
        this.bot_list = bot_list;
        this.mContext = mContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(layoutResourceIds, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        String name = (bot_list.get(position).get(MainActivity.NAME));
        String image = (bot_list.get(position).get(MainActivity.IMAGE));
        String last_msg = (bot_list.get(position).get(MainActivity.LAST_MESSSAGE));

        holder.name.setText(name);
        holder.last_msg.setText(last_msg);

        Picasso.with(mContext).load(Integer.parseInt(image)).into(holder.imageView);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.OnItemClicked(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return bot_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView name;
        TextView last_msg;
        CardView item;

        public ViewHolder(View v) {
            super(v);
            imageView = (CircleImageView) v.findViewById(R.id.img_bot);
            name = (TextView) v.findViewById(R.id.tv_name);
            last_msg = (TextView) v.findViewById(R.id.tv_last_msg);
            item = (CardView) v.findViewById(R.id.card_item);
        }
    }


    public int getCount() {

        return bot_list.size();
    }

    public Object getItem(int position) {

        return position;
    }

    public long getItemId(int position) {

        return position;
    }
}