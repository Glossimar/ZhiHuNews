package com.bignerdranch.android.zhihunews.Functions;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

import com.bignerdranch.android.zhihunews.MainView.MainActivity;

/**
 * Created by LENOVO on 2017/2/21.
 */

public class ViewPagerNew extends ViewPager {

    private long interval;
    static int currentPosition;
    private MyHandler myHandler;
    private String TAG="ViewPagerNew";

    public ViewPagerNew(Context context) {
        super(context);
        Log.d(TAG, "ViewPagerNew: aaaaaaaaaaaaaaaaaaaaaaaab");
        init();
    }

    public ViewPagerNew(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "ViewPagerNew: aaaaaaaaaaaaaaaaaaaaaaaaaaaaW");
        init();
    }
    private void init(){
//        myHandler=new MyHandler(this,layout(););
        currentPosition=1;
//        setCurrentItem(currentPosition,false);
//        MainActivity.tttt.setText("aaaaaaaaaaaaaa");
    }

    public void showNextView(){
        setCurrentItem(currentPosition+1,true);
        Log.d(TAG, "showNextView: aaaaaaaaaaaaaaaaaaaaaa");
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }


}
