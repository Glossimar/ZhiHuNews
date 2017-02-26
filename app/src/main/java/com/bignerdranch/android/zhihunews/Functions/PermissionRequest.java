package com.bignerdranch.android.zhihunews.Functions;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.bignerdranch.android.zhihunews.OpenView.OpenView;

/**
 * Created by LENOVO on 2017/2/26.
 */

public class PermissionRequest {
    private Activity activity;
    private String dialogTitle;
    private int num;

    public PermissionRequest(Activity activity,String dialogTitle,int num){
        this.activity=activity;
        this.dialogTitle=dialogTitle;
        this.num=num;
    }

     public void initPermission() {
        int permission = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (num==1) {
                permission = ContextCompat.checkSelfPermission(activity
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }else {
                permission=ContextCompat.checkSelfPermission(activity
                , Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }

        if (permission != PackageManager.PERMISSION_GRANTED) {
            if (shouldRequest( ))
                return;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (num==2) {
                    ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }else {
                    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                }
        }
    }

    private boolean shouldRequest(){
        if (num == 2) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity
                    ,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setMessage(dialogTitle).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            ActivityCompat.requestPermissions(activity
                                    ,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
                        }
                    }
                }).setNegativeButton("取消",null).create().show();
                return true;
        }else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity
                ,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                    builder.setMessage(dialogTitle).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity
                            ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                        }
                    }).setNegativeButton("取消",null).create().show();
                }
            }


        }
        return false;
    }

//    static void explainDialog(final Activity activity,String dialogName){
//
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode==1&&grantResults.length>0){
//            granted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
//        }
//    }
}
