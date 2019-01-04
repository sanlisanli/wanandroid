package me.mikasa.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.mikasa.wanandroid.R;
import me.mikasa.wanandroid.bean.HomeArticleBean;
import me.mikasa.wanandroid.util.CommonUtil;
import woo.mikasa.lib.base.BaseRvAdapter;

/**
 * Created by mikasa on 2019/1/3.
 */
public class HomeArticleAdapter extends BaseRvAdapter<HomeArticleBean.Data.Datas> {
    private Context mContext;
    public HomeArticleAdapter(Context context){
        this.mContext=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_home_list,parent,false);
        return new HomeListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HomeListHolder)holder).bindView(mDataList.get(position));
    }
    class HomeListHolder extends BaseRvViewHolder{
        TextView title,author,date;
        HomeListHolder(View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.article_title);
            author=itemView.findViewById(R.id.author);
            date=itemView.findViewById(R.id.date);
        }

        @Override
        protected void bindView(HomeArticleBean.Data.Datas datas) {
            title.setText(datas.getTitle());
            author.setText(datas.getAuthor());
            //String time=String.valueOf(datas.getPublishTime());
            //date.setText(CommonUtil.dateFormat(datas.getPublishTime()));
        }
    }
}
