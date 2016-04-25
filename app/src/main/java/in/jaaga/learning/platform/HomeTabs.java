package in.jaaga.learning.platform;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import in.jaaga.learning.R;
import in.jaaga.learning.platform.adapter.TabsPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeTabs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTabs extends Fragment {

    private TabsPagerAdapter adapter=null;

    private FragmentManager fragmentManager;

    ViewPager viewPager;
    private TabLayout tabLayout;

    public HomeTabs() {
        // Required empty public constructor
    }

    public static HomeTabs newInstance() {

        HomeTabs fragment = new HomeTabs();
        fragment.setRetainInstance(true);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();

        adapter = new TabsPagerAdapter
                (fragmentManager);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home_tabs, container, false);

        tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) v.findViewById(R.id.pager);
        if(adapter==null){
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return v;
    }



    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            //throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            //throw new RuntimeException(e);
        }
    }

    public void refresh(){
        int curr_pos =  viewPager.getCurrentItem();
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(curr_pos);
    }

}
