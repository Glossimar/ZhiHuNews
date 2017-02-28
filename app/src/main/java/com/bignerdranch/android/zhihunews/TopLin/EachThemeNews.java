package com.bignerdranch.android.zhihunews.TopLin;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bignerdranch.android.zhihunews.ExtraView.Commits;
import com.bignerdranch.android.zhihunews.Functions.HttpCalBackListener;
import com.bignerdranch.android.zhihunews.Functions.HttpResquest;
import com.bignerdranch.android.zhihunews.Functions.LoadPic;
import com.bignerdranch.android.zhihunews.JSONData.ExtraData;
import com.bignerdranch.android.zhihunews.JSONData.MainNewsData;
import com.bignerdranch.android.zhihunews.JSONData.ThemeNewsData;
import com.bignerdranch.android.zhihunews.MainView.EachMainNews;
import com.bignerdranch.android.zhihunews.R;
import com.google.gson.Gson;

public class EachThemeNews extends AppCompatActivity {

    private  final String TAG ="EachThemeNews" ;
    private Intent intentlast;
    private Intent intentCommit;
    private int themeNewsId;
    private Toolbar toolbar;
    private WebView webView;
    private WebSettings ws;
    private int popularityNew;
//    private ImageView imageView;
    private ThemeNewsData themeNewsData;
    private ExtraData extraData;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_theme_news);
        intentlast=getIntent();
        themeNewsId=intentlast.getIntExtra("EachThemeNewsId",23333);
        Log.d(TAG, "onCreate: "+themeNewsId);
        toolbar=(Toolbar)findViewById(R.id.ToolBar_theme);
//        collapsingToolbarLayout=(CollapsingToolbarLayout)
//                findViewById(R.id.CollapsingToolbarLayout_Theme);
//        imageView=(ImageView)findViewById(R.id.ImageView_theme);
        webView=(WebView)findViewById(R.id.Theme_MianNews_WebView);

        ws=webView.getSettings();

        HttpResquest.http("http://news-at.zhihu.com/api/4/story-extra/"+themeNewsId, new HttpCalBackListener() {
            @Override
            public void onFnish(String responce) {

                Log.d(TAG, "onFnish: 额外信息");
                pareJsonExtra(responce);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        toolbar.setTitle("");
        Log.d(TAG, "onCreate: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        HttpResquest.http("http://news-at.zhihu.com/api/4/news/" + themeNewsId, new HttpCalBackListener() {
            @Override
            public void onFnish(String responce) {
                paresJsonThemeNews(responce);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });

//        HttpResquest.http("http://news-at.zhihu.com/api/4/story-extra/" + themeNewsId, new HttpCalBackListener() {
//            @Override
//            public void onFnish(String responce) {
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
    }


    private void paresJsonThemeNews(String jsonData){
        Gson gson=new Gson();
        themeNewsData=gson.fromJson(jsonData,ThemeNewsData.class);
        Log.d(TAG, "paresJsonThemeNews: "+themeNewsData.getId());
        Log.d(TAG, "paresJsonThemeNews: "+themeNewsData.getTitle());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ws.setJavaScriptEnabled(true);
                ws.setSupportZoom(true);
                ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//                webView.loadUrl("http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3");
                webView.loadDataWithBaseURL(themeNewsData.getCss().get(0),themeNewsData.getBody()
                        ,"text/html","UTF-8",null);
            }
        });
    }

    public void pareJsonExtra(String  jsonData){
        Gson gson=new Gson();
        extraData=gson.fromJson(jsonData,ExtraData.class);

    }

    private void paresJsonExtaData(String jsonData){
        Gson gson=new Gson();
        extraData=gson.fromJson(jsonData,ExtraData.class);

        Log.d(TAG, "paresJsonExtaData:获赞总数 ："+extraData.getPopularity());
        Log.d(TAG, "paresJsonExtaData: 评论总数："+extraData.getComments());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.each_mainnews_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.fabulous:
                if (item.getIcon().getConstantState().equals(getResources()
                        .getDrawable(R.drawable.fabulous).getConstantState())){
                    item.setIcon(R.drawable.fabulous_red);
                    Toast.makeText(this, "这是第"+(extraData.getPopularity()+1)+"个赞啦", Toast.LENGTH_SHORT).show();
                    extraData.setPopularity(extraData.getPopularity()+1);
                }else {
                    item.setIcon(R.drawable.fabulous);
                    Toast.makeText(this, "...QAQ...\n少了一个赞\n只有"+extraData.getPopularity()+"个赞了", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.commit:
                intentCommit=new Intent(EachThemeNews.this, Commits.class);
                intentCommit.putExtra("commitId",themeNewsId);
                startActivity(intentCommit);
                break;
            case R.id.like:
                //
                break;
            case android.R.id.home:
                finish();
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
