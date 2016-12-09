package in.jaaga.learning.platform.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.jaaga.learning.platform.BotListFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new BotListFragment().newInstance();

            case 1:
                return new BotListFragment().newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:

                return "Chat";
            case 1:

                return "Contacts";
        }

        return super.getPageTitle(position);
    }
}