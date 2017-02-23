package com.bignerdranch.android.zhihunews.Functions;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LENOVO on 2017/1/16.
 */

public class HttpResquest extends AppCompatActivity {
    public static void http(final String address,final HttpCalBackListener listener ){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try{
                    URL url=new URL(address);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder responce=new StringBuilder();
                    String line;

                    while ((line=reader.readLine())!=null){
                        responce.append(line);
                    }
                    if (listener!=null){
                          listener.onFnish(responce.toString());

                    }
                }catch (Exception e){
                    if(listener!=null){
                        listener.onError(e);
                    }
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static boolean saveObject(Serializable ser,String name){
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        File f=new File(name);
//        +File.separator+"test.txt"
        try{
            fos=new FileOutputStream(f);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                oos.close();
            }catch (Exception e){
            }
            try {
              fos.close();
            }catch (Exception e){
            }
        }
    }

    public static Serializable readObject(String sm){
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        File f=new File(sm);
//        +File.separator+"test.txt"
        try {
            fis=new FileInputStream(f);
            ois=new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        }catch (FileNotFoundException e){
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                ois.close();
            }catch (Exception e){
            }
            try {
                fis.close();
            }catch (Exception e){
            }
        }
        return null;
    }

    public static boolean isNetworkConnected(Context context){
        if (context!=null){
            ConnectivityManager mConnectivityManager=(ConnectivityManager)context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo!=null){
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifiConnected(Context context){
        if (context!=null){
            ConnectivityManager mConnectivityManager=(ConnectivityManager)context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifiNetworkInfo=mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(mWifiNetworkInfo!=null){
                return mWifiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isMoblileNetworkConnected(Context context){
        if (context!=null){
            ConnectivityManager mConnectivityManager=(ConnectivityManager)context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo=mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo!=null){
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
