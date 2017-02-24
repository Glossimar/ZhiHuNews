package com.bignerdranch.android.zhihunews.TopLin;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bignerdranch.android.zhihunews.Adapter.EditorRecyclerViewAdapter;
import com.bignerdranch.android.zhihunews.Functions.HttpCalBackListener;
import com.bignerdranch.android.zhihunews.Functions.HttpResquest;
import com.bignerdranch.android.zhihunews.Functions.LoadPic;

import com.bignerdranch.android.zhihunews.Adapter.ThemeAdapterNonImage;

import com.bignerdranch.android.zhihunews.Adapter.ThemeMenuAdapter;
//import com.bignerdranch.android.zhihunews.Functions.TopicLinAdapter;
//import com.bignerdranch.android.zhihunews.Functions.aaa;
import com.bignerdranch.android.zhihunews.JSONData.TopLin_Theme;
import com.bignerdranch.android.zhihunews.MainView.MainActivity;
import com.bignerdranch.android.zhihunews.R;
import com.example.picture.CirclelmageViewW;
import com.google.gson.Gson;

//import de.hdodenhof.circleimageview.CircleImageView;

public class TopLin_theme extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewMenu;
    private RecyclerView recyclerViewEditor;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavititionView;
    private ImageView imageView;
    private ListView listView;
    private Intent intent;
    private ImageButton imageButton;
    private Button button;
    private String TAG="TopLin_theme";
    private ThemeAdapterNonImage adapterNonImage;
    private int ID;
    private CirclelmageViewW circleDrawer;
    private CirclelmageViewW circleImageView;
    private GridLayoutManager linearLayoutManager;
    public static  TopLin_Theme topLinTheme;
    public static Toolbar toolbar;

//    private int  idInt=MainActivity.getTopicLin().getOthersClass().get(2).getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.d(TAG, "onCreate: "+id);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_lin_them);
//        listView=(ListView)findViewById(R.id.ListView_theme);
        imageButton=(ImageButton)findViewById(R.id.HomeButtn);
        recyclerViewMenu=(RecyclerView)findViewById(R.id.RecyclerView_Menu_Theme);
        recyclerViewEditor=(RecyclerView)findViewById(R.id.Editor);
        button=(Button)findViewById(R.id.HomeText);
        circleDrawer=(CirclelmageViewW)findViewById(R.id.icon_img);
        imageButton.setOnClickListener(this);
        button.setOnClickListener(this);
        linearLayoutManager=new GridLayoutManager(this,1);
        intent=getIntent();

        ID=intent.getIntExtra("intID",1);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_theme);
        mNavititionView=(NavigationView)findViewById(R.id.navgation_theme);
        imageView=(ImageView)findViewById(R.id.ImageView_1) ;
        circleImageView=(CirclelmageViewW) findViewById(R.id.circleView);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerView_theme1) ;
        recyclerViewMenu=(RecyclerView)findViewById(R.id.RecyclerView_Menu_Theme);
        toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setSupportActionBar(toolbar);

        LoadPic.loadImg(this,"http://pic1.zhimg.com/da8e974dc_m.jpg",circleDrawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        HttpResquest.http( "http://news-at.zhihu.com/api/4/theme/"+ID
        , new HttpCalBackListener() {
                    @Override
                    public void onFnish(String responce) {
                        paresJsonTopTheme(responce.toString());
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.theme_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_theme:
                if (item.getIcon().getConstantState().equals(getResources().getDrawable(R.drawable.follow).getConstantState() )){
                    item.setIcon(R.drawable.nonfollow);
                    Toast.makeText(this, "已关注", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d(TAG, "onOptionsItemSelected: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    item.setIcon(R.drawable.follow);
                    Toast.makeText(this, "取消关注", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        return true;
    }

    public void paresJsonTopTheme(String jsonData) {
        Gson gson = new Gson();
        topLinTheme =gson.fromJson(jsonData, TopLin_Theme.class);
        Log.d(TAG, "paresJsonMenu: litmit" + topLinTheme.getName());
//        Log.d(TAG, "paresJsonMenu: order" + topLinTheme.getStories().get(8).getImages().get(0));
        Log.d(TAG, "paresJsonMenu: others" + topLinTheme.getStories().get(2).getId());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setToolBarTitle(toolbar);
                LoadPic.loadImg(TopLin_theme.this, topLinTheme.getImage(), imageView);
//                LoadPic.loadImg(TopLin_theme.this, topLinTheme.getEditors().get(0).getAvatar(), circleImageView);

                for (int i=0;i<1;i++){
                    LinearLayoutManager linearLayoutManagerEditor=new LinearLayoutManager(TopLin_theme.this);
                    linearLayoutManagerEditor.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerViewEditor.setLayoutManager(linearLayoutManagerEditor);
                    Log.d(TAG, "run: ................................"+ID);
                    recyclerViewEditor.setAdapter(new EditorRecyclerViewAdapter(topLinTheme,ID));
                }

                for (int i = 0; i < topLinTheme.getStories().size(); i++) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(TopLin_theme.this));
                    adapterNonImage = new ThemeAdapterNonImage(topLinTheme.getStories());
                    recyclerView.setAdapter(adapterNonImage);
                    Log.d(TAG, "run: successnon");
                }
//                final TopicLinAdapter adapter = new TopicLinAdapter(TopLin_theme.this,
//                        R.layout.recycler_menu_name,getOthersClass());
                recyclerViewMenu.setLayoutManager(new LinearLayoutManager(TopLin_theme.this));
                recyclerViewMenu.setAdapter(new ThemeMenuAdapter(MainActivity.getTopicLin(),TopLin_theme.this));
               /* int j;

                    if (topLinTheme.getStories().get(i).getImages() != null) {
                        j = 2;
                       *//* Log.d(TAG, "run: "+topLin.getStories().get(i).getImages().get(0)
                                +topLin.getStories().get(i).getTitle());*//*
                    } else {
                        j = 3;
                    }

                    switch (j) {
                        case 2:
                            Log.d(TAG, "run: success"+topLinTheme.getStories().get(i).getImages().get(0)
                                    +topLinTheme.getStories().get(i).getTitle());
                            recyclerView.setLayoutManager(linearLayoutManager);
                            adapterImage = new ThemeAdapterImage(topLinTheme.getStories());
                            recyclerView.setAdapter(adapterImage);
                            break;
                        case 3:
                            recyclerView.setLayoutManager(linearLayoutManager);
                            adapterNonImage = new ThemeAdapterNonImage(topLinTheme.getStories());
                            recyclerView.setAdapter(adapterNonImage);
                            Log.d(TAG, "run: successnon");
                            break;
                        default:
                            break;
                    }*/
                   /* if (topLinTheme.getStories().get(i).getImages() == null) {
                        recyclerView.setLayoutManager(linearLayoutManager);
                        adapterNonImage = new ThemeAdapterNonImage(topLinTheme.getStories());
                        recyclerView.setAdapter(adapterNonImage);
                        Log.d(TAG, "run: successnon");
                    } else {
                        Log.d(TAG, "run: success"+topLinTheme.getStories().get(i).getImages().get(0));
                        recyclerView.setLayoutManager(linearLayoutManager);
                        adapterImage = new ThemeAdapterImage(topLinTheme.getStories());
                        recyclerView.setAdapter(adapterImage);
                    }
                }*/
            }
        });
        }
    public void setToolBarTitle(Toolbar toolbar){
        toolbar.setTitle(TopLin_theme.topLinTheme.getName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.HomeButtn:
                Intent intent=new Intent(TopLin_theme.this,MainActivity.class);
                startActivity(intent);
                finish();

//                finish();

                break;
            case R.id.HomeText:
                Intent intentText=new Intent(TopLin_theme.this,MainActivity.class);
                intentText.putExtra("SettingBool",true);
                startActivity(intentText);
                finish();
        }
    }
}
