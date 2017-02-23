package com.bignerdranch.android.zhihunews.Functions;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.zhihunews.JSONData.MainData;
import com.bignerdranch.android.zhihunews.MainView.EachMainNews;
import com.bignerdranch.android.zhihunews.MainView.MainActivity;

import java.lang.ref.WeakReference;

/**
 * Created by LENOVO on 2017/2/21.
 */

public class MyHandler extends Handler {
    String  TAG="aaaaaaaaaaaaa";
    private TextView textView;
    private MainData mainData;
    private LinearLayout layout;
    private Activity activity;
    public static final int MESSAGE_CHECK=9001;
    private WeakReference<ViewPagerNew> innerObject;

    public MyHandler(ViewPagerNew viewPagerNew, LinearLayout linearLayout, MainData mainData,Activity mainActivity){
        this.mainData=mainData;
        this.layout=linearLayout;
        this.innerObject=new WeakReference<ViewPagerNew>(viewPagerNew);
        this.activity=mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        if (MESSAGE_CHECK==msg.what){
            ViewPagerNew viewPagerNew=innerObject.get();
            if (viewPagerNew.getContext() instanceof Activity){
                Activity activity=(Activity)viewPagerNew.getContext();
                if (activity.isFinishing()){
                    return;
                }
            }
            viewPagerNew.showNextView();
            try {
                if (ViewPagerNew.currentPosition==6||ViewPagerNew.currentPosition==0){}
                else {
                    MainActivity.imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(activity,EachMainNews.class);
                            intent.putExtra("MainNewsItem_id",mainData.getTop_stories().get(ViewPagerNew.currentPosition-1).getId());
                            activity.startActivity(intent);
                        }
                    });
                    MainActivity.tttt.setText(mainData.getTop_stories().get(ViewPagerNew.currentPosition - 1).getTitle());
                    Log.d(TAG, "handleMessage: "+(ViewPagerNew.currentPosition-1));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            removeMessages(MESSAGE_CHECK);
            sendMessageDelayed(obtainMessage(MESSAGE_CHECK),viewPagerNew.getInterval());
        }
        super.handleMessage(msg);
    }
}
