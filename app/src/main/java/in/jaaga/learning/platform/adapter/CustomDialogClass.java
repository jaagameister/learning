package in.jaaga.learning.platform.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import in.jaaga.learning.R;

import static in.jaaga.learning.platform.MainActivity.textToSpeech;

/**
 * Created by Rahul Kumar on 12/9/2016.
 */

public class CustomDialogClass extends Dialog implements View.OnClickListener{
    private Context context;
    private Dialog dialog;
    private String wrd, wrdTp, wrdDef;
    private TextView word, wordType, wordDef;
    private ImageButton speak;
    private Button okay;
    public CustomDialogClass(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        word = (TextView) findViewById(R.id.word);
        word.setText(wrd);
        wordType = (TextView) findViewById(R.id.word_type);
        wordType.setText(wrdTp);
        wordDef = (TextView) findViewById(R.id.word_def);
        wordDef.setText(wrdDef);
        speak = (ImageButton) findViewById(R.id.speak_btn);
        okay = (Button) findViewById(R.id.done_btn);
        speak.setOnClickListener(this);
        okay.setOnClickListener(this);
    }

    public void setValues(String wrd, String wrdTp, String wrdDef){
        this.wrd = wrd;
        this.wrdTp = wrdTp;
        this.wrdDef = wrdDef.replace("\\n", "");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.speak_btn:
                textToSpeech.speak(word.getText().toString());
                break;
            case R.id.done_btn:
                dismiss();
                break;
            default:
                break;
        }
        //dismiss();
    }
}
