package in.jaaga.learning.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import in.jaaga.learning.R;
import in.jaaga.learning.adapter.ChatAdapter;
import in.jaaga.learning.pojo.ChatItem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChatFragmentListener} interface
 * to handle interaction events.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    private ChatFragmentListener mListener;

    private RecyclerView chat_view;
    private EditText chat_box;
    private ArrayList<ChatItem> chat_list;
    private ChatAdapter chatAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chat_list = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        //Setup the list
        chat_view = (RecyclerView) v.findViewById(R.id.chat_view);
        chat_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        chat_view.setLayoutManager(linearLayoutManager);
        chatAdapter = new ChatAdapter(getActivity(),chat_list);
        chat_view.setAdapter(chatAdapter);


        //Setup the chat box
        chat_box = (EditText) v.findViewById(R.id.input_text);
        chat_box.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (chat_box.getText().length() != 0 && chat_box.getText().toString() != "") {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(chat_box.getWindowToken(),
                                InputMethodManager.RESULT_UNCHANGED_SHOWN);

                        String text = chat_box.getText().toString();

                        Send(text);

                    }
                }
                return false;
            }
        });

        return v;
    }

    private void Send(String text){
        ChatItem item = new ChatItem();
        item.setMessage(text);

        //TODO Handle name here,hardcoding for now
        item.setSender("amar");
        chat_list.add(item);
        chatAdapter.notifyItemInserted(chat_list.size()-1);
        chat_box.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChatFragmentListener) {
            mListener = (ChatFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ChatFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface ChatFragmentListener {

        void Send(String text);
    }
}
