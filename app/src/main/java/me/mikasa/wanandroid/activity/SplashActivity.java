package me.mikasa.wanandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.mikasa.wanandroid.R;
import me.mikasa.wanandroid.bean.LoginBean;
import me.mikasa.wanandroid.constants.Constants;
import me.mikasa.wanandroid.http.RetrofitHelper;
import me.mikasa.wanandroid.util.CommonUtil;
import me.mikasa.wanandroid.util.SPUtil;

public class SplashActivity extends AppCompatActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtil.immersiveMode(this);
        setContentView(R.layout.activity_splash);
        mContext=this;
        ImageView iv=findViewById(R.id.iv_splash);
        iv.setImageResource(R.drawable.bg_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jump();
            }
        },3000);
    }
    private void jump(){
        String id=SPUtil.getString(this,"id","nothing");
        if (id.equals("nothing")){
            Intent intentLogin=new Intent(mContext,LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }else {
            Map<String,Object> map=new HashMap<>();
            map.put("username",SPUtil.getString(mContext,"username",""));
            map.put("password",SPUtil.getString(mContext,"password",""));
            RetrofitHelper.getInstance().getApiService()
                    .login(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<LoginBean>() {
                        @Override
                        public void accept(LoginBean bean) throws Exception {
                            Intent intentMain = new Intent(mContext, MainActivity.class);
                            startActivity(intentMain);
                            finish();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Toast.makeText(mContext,"网络错误",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
