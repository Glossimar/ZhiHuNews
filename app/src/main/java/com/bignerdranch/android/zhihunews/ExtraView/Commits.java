package com.bignerdranch.android.zhihunews.ExtraView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;


import com.bignerdranch.android.zhihunews.Adapter.ExtraCommitsAdapter;
import com.bignerdranch.android.zhihunews.Functions.HttpCalBackListener;
import com.bignerdranch.android.zhihunews.Functions.HttpResquest;
import com.bignerdranch.android.zhihunews.Adapter.ShortCommitsELVAdapter;
import com.bignerdranch.android.zhihunews.JSONData.ExtraData;
import com.bignerdranch.android.zhihunews.JSONData.LongCommitsData;
import com.bignerdranch.android.zhihunews.JSONData.ShortCommitsData;
import com.bignerdranch.android.zhihunews.R;
import com.google.gson.Gson;

public class Commits extends AppCompatActivity {

    private TextView textViewLong;
    private Toolbar toolbar;
    private Intent intent;
    private int newsId;
    private ExtraData extraData;
//    private TextView textViewaaa;
    private ShortCommitsData shortCommitsData;
    private ExpandableListView expandableListView;
    private LongCommitsData commentsEntity;
    private RecyclerView recyclerViewLong;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);
        intent=getIntent();
        newsId=intent.getIntExtra("commitId",233333);
//        textViewaaa=(TextView)findViewById(R.id.textaaaaaaaaa);
        expandableListView=(ExpandableListView)findViewById(R.id.ExpandList);
        textViewLong=(TextView)findViewById(R.id.commit_long_text);
        recyclerViewLong=(RecyclerView)findViewById(R.id.commit_long_recycler);
        linearLayoutManager=new LinearLayoutManager(this);
        toolbar=(Toolbar)findViewById(R.id.commit_ToolBar);

        HttpResquest.http("http://news-at.zhihu.com/api/4/story-extra/"+newsId, new HttpCalBackListener() {
            @Override
            public void onFnish(String responce) {
                paresJsonExtra(responce);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });

        HttpResquest.http("http://news-at.zhihu.com/api/4/story/" + newsId + "/long-comments", new HttpCalBackListener() {
            @Override
            public void onFnish(String responce) {
                paresJsonlongCommits(responce);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
        HttpResquest.http("http://news-at.zhihu.com/api/4/story/" + newsId + "/short-comments", new HttpCalBackListener() {
            @Override
            public void onFnish(String responce) {
                paresJsonShortCommit(responce);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void paresJsonExtra(String jsonData){
        Gson gson=new Gson();
        extraData=gson.fromJson(jsonData,ExtraData.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(extraData.getComments() + "条评论");
                setSupportActionBar(toolbar);

                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                textViewLong.setText(extraData.getLong_comments() + "条长评论");
//                textViewaaa.setText("aaaaaaaaaaaaaaaaaaaaaaaa");
            }
        });
    }
    private void paresJsonlongCommits(String jsonData){
        Gson gson=new Gson();
        commentsEntity=gson.fromJson(jsonData,LongCommitsData.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerViewLong.setLayoutManager(linearLayoutManager);
                recyclerViewLong.setAdapter(new ExtraCommitsAdapter(extraData,commentsEntity.getComments()));
            }
        });
    }

    private void paresJsonShortCommit(String jsonData){
        Gson gson=new Gson();
        shortCommitsData=gson.fromJson(jsonData,ShortCommitsData.class);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                expandableListView.setGroupIndicator(null);
                expandableListView.setAdapter(new ShortCommitsELVAdapter(Commits.this
                        ,extraData,shortCommitsData.getComments()));
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
