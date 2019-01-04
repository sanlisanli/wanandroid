package me.mikasa.wanandroid.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import me.mikasa.wanandroid.R;
import me.mikasa.wanandroid.fragment.MainFragment;
import me.mikasa.wanandroid.fragment.MineFragment;
import me.mikasa.wanandroid.fragment.TreeFragment;
import woo.mikasa.lib.base.BaseActivity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private List<Fragment>fragmentList=new ArrayList<>(3);
    private LinearLayout ll_home,ll_tree,ll_mine;
    private ImageView iv_home,iv_tree,iv_mine;
    private MainFragment mainFragment;
    private TreeFragment treeFragment;
    private MineFragment mineFragment;
    @Override
    protected int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mainFragment=new MainFragment();
        treeFragment=new TreeFragment();
        mineFragment=new MineFragment();
        fragmentList.add(mainFragment);
        fragmentList.add(treeFragment);
        fragmentList.add(mineFragment);
    }

    @Override
    protected void initView() {
        Toolbar toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        findView();
        initMain();
    }
    private void initMain(){
        iv_home.setSelected(true);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction tf=manager.beginTransaction();
        tf.replace(R.id.fl_container,mainFragment);
        tf.commit();
    }

    @Override
    protected void initListener() {
        ll_home.setOnClickListener(this);
        ll_tree.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_home:
                setSelected(0);
                setFragment(0);
                break;
            case R.id.main_tree:
                setSelected(1);
                setFragment(1);
                break;
            case R.id.main_mine:
                setSelected(2);
                setFragment(2);
                break;
        }
    }
    private void setFragment(int pos){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction tf=manager.beginTransaction();
        tf.replace(R.id.fl_container,fragmentList.get(pos));
        tf.commit();
    }
    private void setSelected(int position){
        switch (position){
            case 0:
                iv_home.setSelected(true);
                iv_tree.setSelected(false);
                iv_mine.setSelected(false);
                break;
            case 1:
                iv_home.setSelected(false);
                iv_tree.setSelected(true);
                iv_mine.setSelected(false);
                break;
            case 2:
                iv_home.setSelected(false);
                iv_tree.setSelected(false);
                iv_mine.setSelected(true);
                break;
        }
    }
    private void findView(){
        ll_home=findViewById(R.id.main_home);
        ll_tree=findViewById(R.id.main_tree);
        ll_mine=findViewById(R.id.main_mine);
        iv_home=findViewById(R.id.iv_home);
        iv_tree=findViewById(R.id.iv_tree);
        iv_mine=findViewById(R.id.iv_mine);
    }
}
