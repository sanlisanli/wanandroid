package me.mikasa.wanandroid.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by mikasa on 2018/12/28.
 */
public class WanAndroidCookieJar implements CookieJar {
    private static HashMap<String,List<Cookie>>map=new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        map.put(url.host(),cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie>cookies=map.get(url.host());
        return cookies!=null?cookies:new ArrayList<Cookie>();
    }
    public static void clearCookie(){
        map.clear();
    }
}
