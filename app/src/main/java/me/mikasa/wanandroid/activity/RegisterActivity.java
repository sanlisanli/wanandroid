package me.mikasa.wanandroid.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.mikasa.wanandroid.R;
import me.mikasa.wanandroid.bean.RegisterBean;
import me.mikasa.wanandroid.http.RetrofitHelper;
import woo.mikasa.lib.base.BaseToolbarActivity;

public class RegisterActivity extends BaseToolbarActivity {
    private EditText et_account,et_psw,et_psw_again;
    private ProgressBar progressBar;
    @Override
    protected int setLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        mTitle.setText("注册");
    }

    @Override
    protected void initView() {
        et_account=findViewById(R.id.et_account);
        et_psw=findViewById(R.id.et_psw);
        et_psw_again=findViewById(R.id.et_psw_again);
        progressBar=findViewById(R.id.progress_bar);
    }

    @Override
    protected void initListener() {
        findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    private void check(){
        if (TextUtils.isEmpty(et_account.getText().toString().trim())){
            showToast("账号不能为空");
        }else if (TextUtils.isEmpty(et_psw.getText().toString().trim())){
            showToast("密码不能为空");
        }else if (TextUtils.isEmpty(et_psw_again.getText().toString().trim())){
            showToast("密码不能为空");
        }else if (!et_psw.getText().toString().equals(et_psw_again.getText().toString())){
            showToast("两次输入的密码不一样");
        }else {
            register();
        }
    }
    private void register(){
        progressBar.setVisibility(View.VISIBLE);
        Map<String,Object>map=new HashMap<>();
        map.put("username", et_account.getText().toString().trim());
        map.put("password", et_psw.getText().toString().trim());
        map.put("repassword", et_psw_again.getText().toString().trim());
        RetrofitHelper.getInstance().getApiService()
                .register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean bean) throws Exception {
                        progressBar.setVisibility(View.GONE);
                        if (bean.getData()==null){
                            showToast(""+bean.getErrorMsg());
                        }else {
                            showToast("注册成功");
                            finish();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        progressBar.setVisibility(View.GONE);
                        showToast("注册失败");
                    }
                });
    }

}
