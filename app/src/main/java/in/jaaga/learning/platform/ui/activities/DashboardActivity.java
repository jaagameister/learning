package in.jaaga.learning.platform.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.jaaga.learning.R;
import in.jaaga.learning.platform.adapter.TabsPagerAdapter;

/**
 * Created by rajeef on 25/10/16.
 */

public class DashboardActivity extends AppCompatActivity {

    @Bind(R.id.main_dashboard_tabs)
    TabLayout mMainDashboardTabs;

    @Bind(R.id.main_dashboard_viewpager)
    ViewPager mMainDashboardViewPager;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setupViews();
    }

    private void setupViews() {
        TabsPagerAdapter mTabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());
        mMainDashboardViewPager.setAdapter(mTabsPagerAdapter);
        mMainDashboardTabs.setupWithViewPager(mMainDashboardViewPager);
    }

}
