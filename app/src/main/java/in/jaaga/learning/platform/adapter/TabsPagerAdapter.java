package in.jaaga.learning.platform.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.jaaga.learning.R;
import in.jaaga.learning.platform.ui.fragments.BotListFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private static int MAIN_DASHBOARD_TAB_COUNT = 2;
    private String[] tabTitles;

    public TabsPagerAdapter(Context mContext, FragmentManager fm) {
        super(fm);
        tabTitles = mContext.getResources().getStringArray(R.array.main_dashboard_tab_titles);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return BotListFragment.newInstance();
            case 1:
                return BotListFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return MAIN_DASHBOARD_TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}