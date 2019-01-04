package me.mikasa.wanandroid.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by mikasa on 2018/12/28.
 */
public class ShareUtil {
    public static void share(Context context,int stringResId){
        share(context,context.getString(stringResId));
    }
    public static void shareImage(Context context, Uri uri,String title){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setType("image/jpeg");
        context.startActivity(Intent.createChooser(intent,title));
    }
    public static void share(Context context,String s){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"分享");
        intent.putExtra(Intent.EXTRA_TEXT,s);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent,"分享"));
    }
}
