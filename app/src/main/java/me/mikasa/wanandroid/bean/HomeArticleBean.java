package me.mikasa.wanandroid.bean;

import java.util.List;

/**
 * Created by mikasa on 2019/1/3.
 */
public class HomeArticleBean {
    private Data data;
    public void setData(Data d){
        this.data=d;
    }
    public Data getData(){
        return data;
    }
    public static class Data{
        private List<Datas>datas;
        public void setDatas(List<Datas>d){
            this.datas=d;
        }
        public List<Datas>getDatas(){
            return datas;
        }
        public static class Datas{
            private String author;
            private String title;
            private String link;
            private long publishTime;
            public void setPublishTime(long l){
                this.publishTime=l;
            }
            public long getPublishTime(){
                return publishTime;
            }
            public void setLink(String s){
                this.link=s;
            }
            public String getLink(){
                return link;
            }
            public void setAuthor(String s){
                this.author=s;
            }
            public String getAuthor(){
                return author;
            }
            public void setTitle(String s){
                this.title=s;
            }
            public String getTitle(){
                return title;
            }
        }
    }

}
