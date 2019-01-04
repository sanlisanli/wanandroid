package me.mikasa.wanandroid.api;

import java.util.Map;

import io.reactivex.Observable;
import me.mikasa.wanandroid.bean.ArticleListBean;
import me.mikasa.wanandroid.bean.BannerBean;
import me.mikasa.wanandroid.bean.CollectListBean;
import me.mikasa.wanandroid.bean.CollectResultBean;
import me.mikasa.wanandroid.bean.HomeArticleBean;
import me.mikasa.wanandroid.bean.HotKeyBean;
import me.mikasa.wanandroid.bean.KnowledgeTreeBean;
import me.mikasa.wanandroid.bean.LoginBean;
import me.mikasa.wanandroid.bean.RegisterBean;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mikasa on 2018/12/28.
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("user/register")
    Observable<RegisterBean>register(@FieldMap Map<String,Object>map);

    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean>login(@FieldMap Map<String,Object>map);

    //首页文章列表
    @GET("article/list/{page}/json")//{page},@Path,页码，拼接,传入参数page
    Observable<HomeArticleBean>getHomeList(@Path("page")String page);

    //体系数据
    @GET("tree/json")
    Observable<KnowledgeTreeBean>getKnowledgeTree();

    //收藏的站内文章，id
    @POST("lg/collect/{id}/json")
    Observable<CollectResultBean>collectArticle(@Path("id")int id);

    //取消收藏，拼接id,POST
    @POST("lg/uncollect_originId/{id}/json")
    Observable<CollectResultBean>uncollectArticle(@Path("id")int id);

    //知识体系下的文章，cid分类的id
    //http://www.wanandroid.com/article/list/0/json?cid=60
    @GET("article/list/{page}/json")
    Observable<ArticleListBean>getArticleList(@Path("page")int page, @Query("cid")int id);

    //搜索
    @POST("article/query/{page}/json")
    Observable<ArticleListBean>search(@Path("page")int page,@Query("k")String k);

    //收藏文章列表
    @GET("lg/collect/list/{page}/json")
    Observable<CollectListBean>getCollectData(@Path("page")int page);

    @GET("hotkey/json")
    Observable<HotKeyBean>getHotKey();

    @GET("banner/json")
    Observable<BannerBean>getBanner();

    @GET("banner/json")
    Observable<ResponseBody>getBannerBody();
}
