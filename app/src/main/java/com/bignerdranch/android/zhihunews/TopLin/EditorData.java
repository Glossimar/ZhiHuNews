package com.bignerdranch.android.zhihunews.TopLin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bignerdranch.android.zhihunews.Adapter.EditorSpecificAdapter;
import com.bignerdranch.android.zhihunews.Functions.HttpCalBackListener;
import com.bignerdranch.android.zhihunews.Functions.HttpResquest;
import com.bignerdranch.android.zhihunews.JSONData.TopLin_Theme;
import com.bignerdranch.android.zhihunews.R;
import com.google.gson.Gson;

public class EditorData extends AppCompatActivity {

    private  final String TAG = "EditorData";
    private Toolbar toolbar;
    private Intent intent;
    private int themeId;
    private TopLin_Theme topLin_theme;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent=getIntent();
        setContentView(R.layout.activity_editor_data);
        toolbar=(Toolbar)findViewById(R.id.ToolBar_EditorActivity);
        recyclerView=(RecyclerView)findViewById(R.id.Editor_activity_recycler);
        linearLayoutManager=new LinearLayoutManager(this);

        themeId=intent.getIntExtra("ThemeId",2);
        Log.d(TAG, "onCreate: .................."+themeId);
        toolbar.setTitle("主编资料");
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            Log.d(TAG, "onCreate: hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        HttpResquest.http("http://news-at.zhihu.com/api/4/theme/" + themeId, new HttpCalBackListener() {
            @Override
            public void onFnish(String responce) {
                paresJsonEditor(responce);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void paresJsonEditor(String jsonData){
        Gson gson=new Gson();
        topLin_theme=gson.fromJson(jsonData,TopLin_Theme.class);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(new EditorSpecificAdapter(topLin_theme));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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
