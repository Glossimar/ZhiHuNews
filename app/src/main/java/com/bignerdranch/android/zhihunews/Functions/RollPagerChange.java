package com.bignerdranch.android.zhihunews.Functions;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.zhihunews.JSONData.MainData;
import com.bignerdranch.android.zhihunews.JSONData.MainNewsData;
import com.bignerdranch.android.zhihunews.MainView.MainActivity;
import com.bignerdranch.android.zhihunews.R;

import java.sql.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/2/21.
 */

public class RollPagerChange implements ViewPager.OnPageChangeListener {
    private View v;
    private long lastDate;
    private MainData mainData;
    private List<String> textList;
    private TextView imageView;
    private MyHandler myHandler;
    private LinearLayout layout;
    private int pagerAdapterNum;
    Activity mainActivity;


    public RollPagerChange(LinearLayout linearLayout, MainData mainData, int pagerAdapterNum,Activity activity){
        this.mainData=mainData;
        this.layout=linearLayout;
        this.pagerAdapterNum=pagerAdapterNum;
        this.mainActivity=activity;
        myHandler=new MyHandler(MainActivity.viewPager,layout,mainData,mainActivity);

//        this.v=v;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        ViewPagerNew.currentPosition=position;
        if (position==0) {
            int last = ViewPagerNew.currentPosition;
            ViewPagerNew.currentPosition = pagerAdapterNum-2;
        }else if (position==(pagerAdapterNum-1)) {
            int last=ViewPagerNew.currentPosition;
            ViewPagerNew.currentPosition=1;
        }
//        imageView=null;
//        for (int i=0;i<layout.getChildCount();i++){
//            int id=i;
//            Log.d(TAG, "onPageSelected: "+layout.getChildCount());
//            imageView=(TextView) layout.getChildAt(position);
//            if (position==6||position==0){
//                return;
//            }else if (position==ViewPagerNew.currentPosition){
//                imageView.setText(mainData.getTop_stories().get(ViewPagerNew.currentPosition-1).getTitle());
//            }
//        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state){
            case ViewPager.SCROLL_STATE_DRAGGING:
                Log.d(TAG, "onPageScrollStateChanged:       正在滚动 ");
                pauseScroll();
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                Log.d(TAG, "onPageScrollStateChanged:        开始滚动");
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                Log.d(TAG, "onPageScrollStateChanged:         正在设置页面");
                Log.d(TAG, "onPageScrollStateChanged: "+ViewPagerNew.currentPosition);
                if (ViewPagerNew.currentPosition==1) {
                MainActivity.viewPager.setCurrentItem(1,false);
                }
//                if (ViewPagerNew.currentPosition==4)
//                    MainActivity.viewPager.setCurrentItem(5);
//                if (ViewPagerNew.currentPosition==5)
//                    MainActivity.viewPager.setCurrentItem(6);
                logDate();
                resumeScroll();

        }
    }

    public void resumeScroll(){
        myHandler.sendMessageDelayed(myHandler.obtainMessage(MyHandler.MESSAGE_CHECK),5000);
    }

    public void pauseScroll(){
//        if ()
        myHandler.removeMessages(MyHandler.MESSAGE_CHECK);
    }

    private void logDate(){
        long now = new Date(lastDate).getTime();
        if (lastDate==0){
            lastDate=now;
            return;
        }
//        long interval=now-lastDate;
        lastDate=now;
    }
}

