package me.mikasa.wanandroid.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.mikasa.wanandroid.R;
import me.mikasa.wanandroid.bean.LoginBean;
import me.mikasa.wanandroid.http.RetrofitHelper;
import me.mikasa.wanandroid.util.SPUtil;
import woo.mikasa.lib.base.BasePermissionActivity;

public class LoginActivity extends BasePermissionActivity
        implements BasePermissionActivity.PermissionListener {
    private ImageView iv_account_delete,iv_psw_delete;
    private EditText et_account,et_psw;
    private ProgressBar progressBar;

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        Toolbar toolbar=findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        et_account=findViewById(R.id.et_account);
        et_psw=findViewById(R.id.et_psw);
        iv_account_delete=findViewById(R.id.iv_account_delete);
        iv_psw_delete=findViewById(R.id.iv_pwd_delete);
        progressBar=findViewById(R.id.progress_bar);
    }

    @Override
    protected void initListener() {
        et_account.addTextChangedListener(textWatcher);
        et_psw.addTextChangedListener(textWatcher);
        iv_account_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_account.setText("");
                et_account.setHint("账号：");
            }
        });
        iv_psw_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_psw.setText("");
                et_psw.setHint("密码：");
            }
        });
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();

            }
        });
        findViewById(R.id.register_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void login(){
        progressBar.setVisibility(View.VISIBLE);
        Map<String,Object>map=new HashMap<>();
        map.put("username",et_account.getText().toString().trim());
        map.put("password",et_psw.getText().toString().trim());
        RetrofitHelper.getInstance().getApiService()
                .login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean bean) throws Exception {
                        progressBar.setVisibility(View.GONE);
                        if (bean.getData()==null){
                            showToast(""+bean.getErrorMsg());
                        }else {//登录成功
                            showToast("登录成功");
                            saveUserInfo(bean);
                            Intent intent=new Intent(mContext,MainActivity.class);
                            startActivity(intent);
                            finish();//finish()
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        progressBar.setVisibility(View.GONE);
                        showToast("账号或密码错误");
                    }
                });
    }
    private void check(){
        if (TextUtils.isEmpty(et_account.getText().toString().trim())){
            showToast("账号不能为空");
        }else if (TextUtils.isEmpty(et_psw.getText().toString().trim())){
            showToast("密码不能为空");
        }else {
            login();
        }
    }
    @Override
    protected void initAll() {
        String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
        requestRuntimePermission(permissions,this);
    }

    @Override
    public void onGranted() {
        initData();
        initView();
        initListener();
    }

    @Override
    public void onDenied() {

    }
    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            if (et_account.getText().toString().length()>0){
                iv_account_delete.setVisibility(View.VISIBLE);
            }else {
                iv_account_delete.setVisibility(View.INVISIBLE);
            }
            if (et_psw.getText().toString().length()>0){
                iv_psw_delete.setVisibility(View.VISIBLE);
            }else {
                iv_psw_delete.setVisibility(View.INVISIBLE);
            }
        }
    };
    private void saveUserInfo(LoginBean bean){
        SPUtil.putString(mContext,"username",bean.getData().getUsername());
        SPUtil.putString(mContext,"password",bean.getData().getPassword());
        SPUtil.putString(mContext,"id",bean.getData().getId()+"");
    }
}
