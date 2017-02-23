package com.bignerdranch.android.zhihunews.Functions;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by LENOVO on 2017/1/16.
 */

public class LoadPic {
    public static void loadImg(Activity activity,final String addImg ,ImageView imgView ){
        Glide.with(activity).load(addImg).into(imgView);
    }
    public static void loadImgContext(Context context,final String addImg,ImageView imageView){
        Glide.with(context).load(addImg).into(imageView);
    }
}
