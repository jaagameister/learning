package in.jaaga.learning.platform;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import java.io.IOException;
import java.util.Locale;

import in.jaaga.learning.R;
import in.jaaga.learning.platform.helper.DatabaseHelper;
import in.jaaga.learning.t2s.Speech;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity implements ChatFragment.ChatFragmentListener,BotListFragment.BotListFragmentInterface {

    FragmentManager fragmentManager;

    //Chat bot params
    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String LAST_MESSSAGE = "last_message";

    public static Speech textToSpeech;
    public static DatabaseHelper myDbHelper;

    View decorView;
    int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_main);

        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
        
        textToSpeech = new Speech(MainActivity.this);
        myDbHelper = new DatabaseHelper(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myDbHelper.createDataBase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        fragmentManager = getSupportFragmentManager();
        showHomeFragment();

    }

    //Show the list of bots available!
    private void showHomeFragment(){

        if(fragmentManager!=null){
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_container,BotListFragment.newInstance(),"BotListFragment");
            transaction.commit();
        }
    }


    @Override
    public void onBackPressed() {

        if(getSupportFragmentManager().getBackStackEntryCount()<=0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit the app ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }else{
            super.onBackPressed();
        }
    }

    /*Locale myLocale;

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Bundle bundle = null;
        if(chatFragment!=null){
            bundle = new Bundle();
            bundle.putSerializable("chat", chatFragment.getList());
        }

        Intent refresh = new Intent(this, MainActivity.class);
        refresh.putExtras(bundle);
        startActivity(refresh);
        finish();
    }*/

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        textToSpeech.destroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        textToSpeech = new Speech(MainActivity.this);
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDbHelper.close();
        textToSpeech.destroy();
    }

    @Override
    public void switchToFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container,fragment,"chatFragment")
                .addToBackStack(String.valueOf(fragment.getId()))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitAllowingStateLoss();
    }
}
