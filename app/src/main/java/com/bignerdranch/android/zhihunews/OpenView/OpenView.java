package com.bignerdranch.android.zhihunews.OpenView;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.zhihunews.Functions.ActivityCloser;
import com.bignerdranch.android.zhihunews.Functions.HttpCalBackListener;
import com.bignerdranch.android.zhihunews.Functions.HttpResquest;
import com.bignerdranch.android.zhihunews.Functions.LoadPic;
import com.bignerdranch.android.zhihunews.JSONData.OpenData;
import com.bignerdranch.android.zhihunews.JSONData.OpenDataNew;
import com.bignerdranch.android.zhihunews.MainView.MainActivity;
import com.bignerdranch.android.zhihunews.R;
import com.google.gson.Gson;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class OpenView extends AppCompatActivity {

    private File  file;
    private TextView textView;
    private ImageView imgView;
    private OpenDataNew openNew;
    private String openDataSaved;
    private String TAG="OpenView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_view);
        textView = (TextView) findViewById(R.id.text);
        imgView = (ImageView) findViewById(R.id.img_open);
        file = Environment.getExternalStorageDirectory();
        final String fileName = file.toString() + File.separator + "open.txt";

        if (HttpResquest.isNetworkConnected(this)) {
            Log.d(TAG, "onCreate: 网络已连接");
            HttpResquest.http("https://news-at.zhihu.com/api/7/prefetch-launch-images/1080*1668",
                    new HttpCalBackListener() {
                        @Override
                        public void onFnish(String responce) {
                            HttpResquest.saveObject(responce, fileName);
                            openDataSaved = (String) HttpResquest.readObject(fileName);
                            Log.d(TAG, "onFnish: openDataSaved不是空的，已经缓存成功" + openDataSaved);
                            parseJosnOpen(responce);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });

        } else {
            Log.d(TAG, "onCreate: 网络未连接");
            openDataSaved = (String) HttpResquest.readObject(fileName);

            if (openDataSaved == null) {
                Log.d(TAG, "onCreate:缓存没有成功，openDataSaved是空的 ");
                Toast.makeText(this, "网络好像有问题\n。。。QAQ。。。而且连缓存都没有。。。\n心痛到无法呼吸"
                        , Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "网络好像有问题。。。QAQ\n。。。只能先看看以前的了。。。\n心痛到无法呼吸"
                        , Toast.LENGTH_LONG).show();
                parseJosnOpen(openDataSaved);
            }

        }

        final Intent intent = new Intent(OpenView.this, MainActivity.class);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(timerTask, 5000);
    }





    public void parseJosnOpen(String jsonData) {
        Gson gson = new Gson();
        openNew = gson.fromJson(jsonData, OpenDataNew.class);
        Log.d("MainACtivity", "parseJosn: @" + openNew.getCreatives().get(0).getText());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("@"+openNew.getCreatives().get(0).getText());
                LoadPic.loadImg(OpenView.this, openNew.getCreatives().get(0).getUrl(),imgView);
            }
        });
    }
}

