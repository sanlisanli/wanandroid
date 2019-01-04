package me.mikasa.wanandroid.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import me.mikasa.wanandroid.R;
import woo.mikasa.lib.base.BaseToolbarActivity;

public class WebViewActivity extends BaseToolbarActivity {
    private ProgressBar webProgress;
    private WebView webView;

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initData() {
        mTitle.setText(getIntent().getStringExtra("title"));
    }

    @Override
    protected void initView() {
        webProgress=findViewById(R.id.web_progress);
        String url=getIntent().getStringExtra("url");
        webView=findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(chromeClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onBackPressed() {
        if ( webView!= null && webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
    WebChromeClient chromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            webProgress.setProgress(newProgress);
            if (newProgress==100){
                webProgress.setVisibility(View.GONE);
            }
        }
    };
}
