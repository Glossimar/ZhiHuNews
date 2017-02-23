package com.bignerdranch.android.zhihunews.MainView;

import android.content.Intent;
import android.media.Image;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.zhihunews.ExtraView.Commits;
import com.bignerdranch.android.zhihunews.Functions.HttpCalBackListener;
import com.bignerdranch.android.zhihunews.Functions.HttpResquest;
import com.bignerdranch.android.zhihunews.Functions.LoadPic;
import com.bignerdranch.android.zhihunews.JSONData.ExtraData;
import com.bignerdranch.android.zhihunews.JSONData.MainNewsData;
import com.bignerdranch.android.zhihunews.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.File;

public class EachMainNews extends AppCompatActivity {

    private  final String TAG ="EachMainNews" ;
    private Intent intentlast;
    private Intent intentCommit;
    private int mainNewsId;
    private Toolbar toolbar;
    private WebView webView;
    private WebSettings ws;
    private TextView textYiyle;
    private String JSON_NEXT_1;
    private String JSON_NEXT_2;
    private String JSON_NEXT_3;
    private String JSON_NEXT_4;
    private String JSON_NEXT_5;
    private ImageView imageView;
    private MainNewsData mainNewsData;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_main_news);
        toolbar=(Toolbar)findViewById(R.id.ToolBar);
        collapsingToolbarLayout=(CollapsingToolbarLayout)
                findViewById(R.id.CollapsingToolbarLayout);
        textYiyle=(TextView)findViewById(R.id.text_title);
        imageView=(ImageView)findViewById(R.id.ImageView);
        webView=(WebView)findViewById(R.id.Each_MianNews_WebView);
        ws=webView.getSettings();

        toolbar.setTitle("");
//        menuCommitLike.setText("(");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        intentlast=this.getIntent();
        mainNewsId=intentlast.getIntExtra("MainNewsItem_id",33333);

        Log.d(TAG, "onCreate: "+mainNewsId);
//        File file = Environment.getExternalStorageDirectory();
//        String name = file.toString() + File.separator + "test1" + ".txt";
//        JSON_NEXT_1=(String) HttpResquest.readObject(name);
//        if ()
        HttpResquest.http("http://news-at.zhihu.com/api/4/news/"+mainNewsId, new HttpCalBackListener() {
            @Override
            public void onFnish(String responce) {
                pareJsonMainNews(responce);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void pareJsonMainNews(String jsonData){
        Gson gson=new Gson();
        mainNewsData=gson.fromJson(jsonData,MainNewsData.class);
        Log.d(TAG, "pareJsonMainNews:"+mainNewsData.getCss().get(0));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                collapsingToolbarLayout.setTitle(mainNewsData.getTitle());
                textYiyle.setText(mainNewsData.getTitle());
                LoadPic.loadImg(EachMainNews.this,mainNewsData.getImage(),imageView);
                ws.setJavaScriptEnabled(true);
                ws.setSupportZoom(true);
                ws.setTextSize(WebSettings.TextSize.NORMAL);
                ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                webView.loadDataWithBaseURL(mainNewsData.getCss().get(0),mainNewsData.getBody()
                        ,"text/html","UTF-8",null);
            }
        });
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
                }else {
                    item.setIcon(R.drawable.fabulous);
                }
                break;
            case R.id.commit:
                intentCommit=new Intent(EachMainNews.this, Commits.class);
                intentCommit.putExtra("commitId",mainNewsId);
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
