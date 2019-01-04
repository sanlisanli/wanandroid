package me.mikasa.wanandroid.bean;

import java.util.List;

/**
 * Created by mikasa on 2018/12/28.
 */
public class BannerBean {

    private int errorCode;
    private Object errorMsg;
    private List<DataBean>data;//data
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setData(List<DataBean>data){
        this.data=data;
    }
    public List<DataBean>getData(){
        return data;
    }
    public static class DataBean{
        @Override
        public String toString() {
            return "DataBean{" +
                    "imagePath=" + imagePath +
                    ", title=" + title +
                    '}';
        }

        /**
         *      "desc": "一起来做个App吧",
         * 		"id": 10,
         * 		"imagePath": "http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
         * 		"isVisible": 1,
         * 		"order": 3,
         * 		"title": "一起来做个App吧",
         * 		"type": 0,
         * 		"url": "http://www.wanandroid.com/blog/show/2"
         */

        private String imagePath;
        private String title;
        private String url;
        public void setUrl(String s){
            this.url=s;
        }
        public String getUrl(){
            return url;
        }
        public void setImagePath(String s){
            this.imagePath=s;
        }
        public String getImagePath(){
            return imagePath;
        }
        public void setTitle(String s){
            this.title=s;
        }
        public String getTitle(){
            return title;
        }
    }
}
