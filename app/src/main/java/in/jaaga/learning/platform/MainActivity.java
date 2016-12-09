package in.jaaga.learning.platform;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

    Button english, marathi;
    Button kannada, spanish, hindi;

    private ChatFragment chatFragment;

    View decorView;
    int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        // Note that system bars will only be "visible" if none of the
                        // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            // TODO: The system bars are visible.
                            decorView.setSystemUiVisibility(uiOptions);
                        } else {
                            // TODO: The system bars are NOT visible.
                            decorView.setSystemUiVisibility(uiOptions);
                        }
                    }
                });

        kannada=(Button) findViewById(R.id.kannada);
        kannada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("kn");
            }
        });

        english=(Button) findViewById(R.id.english);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
            }
        });

        spanish=(Button) findViewById(R.id.spanish);
        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("es");
            }
        });

        marathi=(Button) findViewById(R.id.marathi);
        marathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("mr");
            }
        });
        
        hindi=(Button) findViewById(R.id.hindi);
        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("hi");
            }
        });

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
            transaction.replace(R.id.content_container,new HomeTabs().newInstance(),"homeTabsFragment");
            transaction.commit();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    Locale myLocale;

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
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        getMenuInflater().inflate(R.menu.select_menu, menu);
        return true;
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
