package me.mikasa.wanandroid.fragment;

import android.os.Bundle;

import me.mikasa.wanandroid.R;
import woo.mikasa.lib.base.BaseFragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.mikasa.wanandroid.fragment.child.CommonFragment;
import me.mikasa.wanandroid.fragment.main.HomeListFragment;

/**
 * Created by mikasa on 2019/1/3.
 */
public class MainFragment extends BaseFragment{
    private List<Fragment>fragmentList;
    private String[] titles;
    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle bundle) {
        titles=mBaseActivity.getResources().getStringArray(R.array.home_tab_titles);
        fragmentList=new ArrayList<>(titles.length);
        fragmentList.add(new HomeListFragment());
        for (int i=0;i<titles.length-1;i++){
            fragmentList.add(new CommonFragment());
        }
    }

    @Override
    protected void initView() {
        ViewPager viewPager=mRootView.findViewById(R.id.main_vp);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new MainAdapter(getChildFragmentManager()));
        viewPager.setCurrentItem(0);
        TabLayout tabLayout=mRootView.findViewById(R.id.main_tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void setListener() {
    }
    private class MainAdapter extends FragmentPagerAdapter {
        MainAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
