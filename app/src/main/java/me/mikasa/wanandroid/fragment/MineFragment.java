package me.mikasa.wanandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.mikasa.wanandroid.R;
import me.mikasa.wanandroid.activity.LoginActivity;
import me.mikasa.wanandroid.http.WanAndroidCookieJar;
import me.mikasa.wanandroid.util.SPUtil;
import woo.mikasa.lib.base.BaseFragment;

/**
 * Created by mikasa on 2019/1/3.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int setLayoutResId() {
        return R.layout.fargment_mine;
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setListener() {
        mRootView.findViewById(R.id.ll_star).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_exit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_exit:
                exitAccount();
                break;
        }
    }
    private void exitAccount(){
        WanAndroidCookieJar.clearCookie();
        SPUtil.deleteAll(mBaseActivity,SPUtil.NAME);
        Intent intent=new Intent(mBaseActivity,LoginActivity.class);
        startActivity(intent);
        mBaseActivity.finish();
    }
}
