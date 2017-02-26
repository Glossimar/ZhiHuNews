package com.bignerdranch.android.zhihunews.OpenView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import com.bignerdranch.android.zhihunews.Functions.PermissionRequest;
import com.bignerdranch.android.zhihunews.JSONData.OpenData;
import com.bignerdranch.android.zhihunews.JSONData.OpenDataNew;
import com.bignerdranch.android.zhihunews.MainView.MainActivity;
import com.bignerdranch.android.zhihunews.R;
import com.google.gson.Gson;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.Manifest;

public class OpenView extends AppCompatActivity {

    private File file;
    private int permissionNum1;
    private PermissionRequest permissionRequestWrite;
    private PermissionRequest permissionRequestRead;
    private boolean granted;
    private TextView textView;
    private ImageView imgView;
    private OpenDataNew openNew;
    private String openDataSaved;
    private String TAG = "OpenView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_view);
        permissionNum1=1;
        permissionRequestWrite=new PermissionRequest(OpenView.this,"应用需要获取你的缓存权限，是否授权？",permissionNum1);
        permissionNum1=2;
        permissionRequestRead=new PermissionRequest(OpenView.this,"应用需要获取你的缓存权限，是否授权？",permissionNum1);

        textView = (TextView) findViewById(R.id.text);
        imgView = (ImageView) findViewById(R.id.img_open);
        file = Environment.getExternalStorageDirectory();
        final String fileName = file.toString() + File.separator + "open.txt";
        permissionRequestWrite.initPermission();
        permissionRequestRead.initPermission();
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
                textView.setText("@" + openNew.getCreatives().get(0).getText());
                LoadPic.loadImg(OpenView.this, openNew.getCreatives().get(0).getUrl(), imgView);
            }
        });
    }

    private void initPermission() {
        int permission = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            permission = ContextCompat.checkSelfPermission(OpenView.this
                    , android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (permission != PackageManager.PERMISSION_GRANTED) {
            if (shouldRequest())
                return;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.requestPermissions(OpenView.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    private boolean shouldRequest(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this
                ,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
            explainDialog();
            return true;
        }
        return false;
    }

    private void explainDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("应用需要获取你的缓存权限，是否授权？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityCompat.requestPermissions(OpenView.this
                            ,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
            }
        }).setNegativeButton("取消",null).create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1&&grantResults.length>0){
            granted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
        }
    }
}


