package me.mikasa.wanandroid.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.CustomFooterViewCallBack;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.mikasa.wanandroid.R;
import me.mikasa.wanandroid.activity.WebViewActivity;
import me.mikasa.wanandroid.adapter.HomeArticleAdapter;
import me.mikasa.wanandroid.bean.BannerBean;
import me.mikasa.wanandroid.bean.HomeArticleBean;
import me.mikasa.wanandroid.http.RetrofitHelper;
import woo.mikasa.lib.base.BaseFragment;
import woo.mikasa.lib.base.BaseRvAdapter;

/**
 * Created by mikasa on 2019/1/3.
 */
public class HomeListFragment extends BaseFragment
        implements BaseRvAdapter.OnRvItemClickListener {
    private XRecyclerView recyclerView;
    private HomeArticleAdapter adapter;
    private int page=0;
    private Banner banner;
    private List<BannerBean.DataBean>bannerList=new ArrayList<>();
    private List<HomeArticleBean.Data.Datas> articleList=new ArrayList<>();
    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected void initData(Bundle bundle) {
        adapter=new HomeArticleAdapter(mBaseActivity);
    }

    @Override
    protected void initView() {
        recyclerView=mRootView.findViewById(R.id.xrv_home_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        recyclerView.setAdapter(adapter);
        recyclerView.setLimitNumberToCallLoadMore(1);
        View bannerView=LayoutInflater.from(mBaseActivity).inflate(R.layout.layout_home_header,recyclerView,false);
        banner=bannerView.findViewById(R.id.home_banner);
        recyclerView.addHeaderView(bannerView);
        View footerView=LayoutInflater.from(mBaseActivity).inflate(R.layout.layout_load_more_footer,recyclerView,false);
        recyclerView.setFootView(footerView, new CustomFooterViewCallBack() {
            @Override
            public void onLoadingMore(View view) {
            }

            @Override
            public void onLoadMoreComplete(View yourFooterView) {
            }

            @Override
            public void onSetNoMore(View yourFooterView, boolean noMore) {
            }
        });
    }

    @Override
    protected void setListener() {
        adapter.setOnRvItemClickListener(this);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                ++page;
                getHomeArticle();
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //showToast(""+position+" "+bannerList.get(position).getImagePath());
                Intent intent=new Intent(mBaseActivity,WebViewActivity.class);
                intent.putExtra("url",bannerList.get(position).getUrl());
                intent.putExtra("title",bannerList.get(position).getTitle());
                startActivity(intent);
            }
        });
        getBannerData();//第一次请求网络数据
        getHomeArticle();
    }
    private void getBannerData(){
        RetrofitHelper.getInstance().getApiService()
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerBean>() {
                    @Override
                    public void accept(BannerBean bannerBean) throws Exception {
                        bannerList=bannerBean.getData();
                        setBannerImage(bannerBean);
                    }
                });
    }
    private void getHomeArticle(){
        RetrofitHelper.getInstance().getApiService()
                .getHomeList(String.valueOf(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeArticleBean>() {
                    @Override
                    public void accept(HomeArticleBean homeArticleBean) throws Exception {
                        recyclerView.loadMoreComplete();
                        articleList.addAll(homeArticleBean.getData().getDatas());
                        if (page==0){
                            adapter.refreshData(homeArticleBean.getData().getDatas());
                        }else {
                            adapter.appendData(homeArticleBean.getData().getDatas());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showToast("网络数据请求失败");
                    }
                });
    }
    private void setBannerImage(BannerBean bean){
        List<String>urls=new ArrayList<>(bean.getData().size());
        for (int i=0;i<bean.getData().size();i++){
            urls.add(bean.getData().get(i).getImagePath());
        }
        banner.setImages(urls).setImageLoader(new BannerImageLoader())
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setBannerAnimation(Transformer.DepthPage)
                .start();
    }

    @Override
    public void onItemClick(int pos) {
        //showToast(""+pos+" "+articleList.get(pos-2).getTitle());
        Intent intent=new Intent(mBaseActivity,WebViewActivity.class);
        intent.putExtra("title",articleList.get(pos-2).getTitle());
        intent.putExtra("url",articleList.get(pos-2).getLink());
        startActivity(intent);
    }
    private class BannerImageLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(mBaseActivity)
                    .load((String)path)
                    .crossFade()
                    .error(R.drawable.ic_bili)
                    .into(imageView);
        }
    }
}
