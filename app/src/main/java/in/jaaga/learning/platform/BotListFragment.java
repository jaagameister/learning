package in.jaaga.learning.platform;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import in.jaaga.learning.R;
import in.jaaga.learning.bots.EchoBot;
import in.jaaga.learning.bots.GrammarBot;
import in.jaaga.learning.bots.Anuj;
import in.jaaga.learning.bots.NumberGuess;
import in.jaaga.learning.bots.PictureBook;
//import in.jaaga.learning.bots.TranslatorBot;
import in.jaaga.learning.bots.TranslatorBot;
import in.jaaga.learning.bots.skillbot.MathBot;
import in.jaaga.learning.platform.adapter.BotListAdapter;
import in.jaaga.learning.platform.adapter.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BotListFragmentInterface} interface
 * to handle interaction events.
 * Use the {@link BotListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BotListFragment extends Fragment implements OnItemClickListener {

    private RecyclerView bot_list_view;
    private BotListFragmentInterface mListener;
    private BotListAdapter adapter_bot_list;
    private static ArrayList<HashMap<String, String>> bot_list;

    public BotListFragment() {

        /* TODO  Hardcoded data needs to be replaced
        *  Get the list of available bots from some source.
        *******************************************************************************/
        bot_list = new ArrayList<>();
        HashMap<String,String> bot = new HashMap<>();

        bot.put(MainActivity.NAME,"Srini");
        bot.put(MainActivity.IMAGE,String.valueOf(R.drawable.srini));
        bot.put(MainActivity.LAST_MESSSAGE,"Srini here!");
        bot_list.add(bot);

        bot = new HashMap<>();
        bot.put(MainActivity.NAME,"Pratham Ji");
        bot.put(MainActivity.IMAGE,String.valueOf(R.drawable.pratham));
        bot.put(MainActivity.LAST_MESSSAGE,"Let me tell you a story.");
        bot_list.add(bot);

        bot = new HashMap<>();
        bot.put(MainActivity.NAME,"Number Guess");
        bot.put(MainActivity.IMAGE,String.valueOf(R.drawable.number_guess));
        bot.put(MainActivity.LAST_MESSSAGE,"Think of a number any number...");
        bot_list.add(bot);

        bot = new HashMap<>();
        bot.put(MainActivity.NAME,"Grammar Bot");
        bot.put(MainActivity.IMAGE,String.valueOf(R.drawable.grammar_bot));
        bot.put(MainActivity.LAST_MESSSAGE,"Who ya gonna call!");
        bot_list.add(bot);

        bot = new HashMap<>();
        bot.put(MainActivity.NAME,"Bing");
        bot.put(MainActivity.LAST_MESSSAGE,"Translate anything you want..");
        bot.put(MainActivity.IMAGE,String.valueOf(R.drawable.bing));
        bot_list.add(bot);

        bot = new HashMap<>();
        bot.put(MainActivity.NAME,"Conversation Bot");
        bot.put(MainActivity.IMAGE,String.valueOf(R.drawable.conversation));
        bot.put(MainActivity.LAST_MESSSAGE,"Who ya gonna call!");
        bot_list.add(bot);


        bot = new HashMap<>();
        bot.put(MainActivity.NAME,"Echo Bot");
        bot.put(MainActivity.IMAGE,String.valueOf(R.drawable.echo));
        bot.put(MainActivity.LAST_MESSSAGE,"I'm your Echo!");
        bot_list.add(bot);

        /********************************************************************************/
    }


    public static BotListFragment newInstance() {
        BotListFragment fragment = new BotListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bot_list, container, false);

        adapter_bot_list = new BotListAdapter(getActivity(), R.layout.bot_list_adapter, bot_list,this);

        bot_list_view = (RecyclerView) v.findViewById(R.id.rv_bot_list);
        bot_list_view.setHasFixedSize(true);
        setLayoutManager();
        bot_list_view.setAdapter(adapter_bot_list);

        return v;
    }

    private void setLayoutManager() {

        LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(getActivity());
        bot_list_view.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BotListFragmentInterface) {
            mListener = (BotListFragmentInterface) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnItemClicked(int position) {

        switch (position){
            case 0:

                switchToFragment(ChatFragment.newInstance(new MathBot()));

                break;
            case 1:

                switchToFragment(ChatFragment.newInstance(new PictureBook()));

                break;

            case 2:

                switchToFragment(ChatFragment.newInstance(new NumberGuess()));

                break;

            case 3:

                switchToFragment(ChatFragment.newInstance(new GrammarBot()));

                break;

            case 4:

                switchToFragment(ChatFragment.newInstance(new TranslatorBot()));

                break;

            case 5:

                switchToFragment(new ChatFragment().newInstance(new Anuj()));

                break;

            case 6:

                switchToFragment(ChatFragment.newInstance(new EchoBot(getActivity())));

                break;
        }


    }

    private void switchToFragment(Fragment fragment) {

        mListener.switchToFragment(fragment);
    }

    @Override
    public void OnItemLongClicked(int position, int state) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface BotListFragmentInterface {
        // TODO: Update argument type and name
        void switchToFragment(Fragment fragment);
    }
}
