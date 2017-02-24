package com.bignerdranch.android.zhihunews.MainView;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.zhihunews.Adapter.RollViewPagerAdapter;
import com.bignerdranch.android.zhihunews.Functions.HttpCalBackListener;
import com.bignerdranch.android.zhihunews.Functions.HttpResquest;
//import com.bignerdranch.android.zhihunews.Functions.TopcLinAdapter;
import com.bignerdranch.android.zhihunews.Adapter.MainNewsAdapter;
import com.bignerdranch.android.zhihunews.Adapter.RollViewAdapter;
import com.bignerdranch.android.zhihunews.Adapter.ThemeMenuAdapter;
//import com.bignerdranch.android.zhihunews.Functions.TopicLinAdapterter;
import com.bignerdranch.android.zhihunews.Functions.LoadPic;
import com.bignerdranch.android.zhihunews.Functions.RollPagerChange;
import com.bignerdranch.android.zhihunews.Functions.ViewPagerNew;
import com.bignerdranch.android.zhihunews.JSONData.MainData;
import com.bignerdranch.android.zhihunews.JSONData.TopicLin;
import com.bignerdranch.android.zhihunews.R;
import com.example.picture.CirclelmageViewW;
import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "MainActivity";

    static public TextView tttt;
    private List<ImageView> imageViewList;
     private List<String> textList;
    private LinearLayout layout;
    static public ImageView imageView;
    private LinearLayout.LayoutParams layoutParams;
    public static ViewPagerNew viewPager;
    private RollPagerChange rollPagerChange;


    private Intent intent;
    private TextView mTextViewPager;
    private TextView mainDateText;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private NavigationView naviView;
    private ImageView imageButton;
    static TopicLin topicLin;
    private ImageButton imageButton2;
    private Button button;
    private MainData mainData;
    private String JSONDATA_SER;
    private String name;
    private Intent intentTheme;
    private CirclelmageViewW circleDrawer;
    private RecyclerView recyclerViewMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=(ViewPagerNew)findViewById(R.id.rollViewPager);
        layout=(LinearLayout)findViewById(R.id.dot);
        tttt=(TextView)findViewById(R.id.ddddd);



        intentTheme=new Intent();
        mainDateText = (TextView) findViewById(R.id.main_date_text);
        mTextViewPager = (TextView) findViewById(R.id.text_viewPager);
        recyclerViewMenu=(RecyclerView)findViewById(R.id.RecyclerView_Menu);
        circleDrawer=(CirclelmageViewW)findViewById(R.id.icon_img);
        imageButton = (ImageView) findViewById(R.id.imageButton);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        naviView = (NavigationView) findViewById(R.id.navgation);
        imageButton2 = (ImageButton) findViewById(R.id.HomeButtn);
        button = (Button) findViewById(R.id.HomeText);

        File file=Environment.getExternalStorageDirectory();
        name=file.toString()+File.separator+"test.txt";


        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolBar);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        imageButton2.setOnClickListener(this);
        button.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        LoadPic.loadImg(this,"http://pic1.zhimg.com/da8e974dc_m.jpg",circleDrawer);
        naviView.setCheckedItem(R.id.button);
        naviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Toast.makeText(MainActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        HttpResquest.http("http://news-at.zhihu.com/api/4/themes", new HttpCalBackListener() {
            @Override
            public void onFnish(String responce) {

                paresJsonMenu(responce);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
//
//        if (HttpResquest.isNetworkConnected(this)) {
//            if (HttpResquest.isWifiConnected(this)) {
//                HttpResquest.http("http://news-at.zhihu.com/api/4/news/latest", new HttpCalBackListener() {
//                    @Override
//                    public void onFnish(String responce) {
//
//                        Log.d(TAG, "onFnish: 。。。。。。。。"+name+".........");
//                        HttpResquest.saveObject(responce,name);
//                        if (HttpResquest.readObject(name)!=null) {
//                            JSONDATA_SER = (String) HttpResquest.readObject(name);
//                            Log.d(TAG, "onFnish: JSONDATA_SRE不是空的缓存已经成功" + JSONDATA_SER);
//                            paresJsonMain(JSONDATA_SER);
//                            Log.d(TAG, "onFnish: ddddddddddddddddddddddddddddddddddddddddddddddddd");
//                        }else{
//                            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Log.d(TAG, "onError: eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
//                        e.printStackTrace();
//                    }
//                });
//            }
//        } else if (HttpResquest.isMoblileNetworkConnected(this)||!HttpResquest.isNetworkConnected(this)) {
//
//            if (JSONDATA_SER != null) {
//                Log.d(TAG, "onCreate: ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
//                JSONDATA_SER = (String) HttpResquest.readObject(name);
//                paresJsonMain(JSONDATA_SER);
//            } else {
//                Log.d(TAG, "onCreate: JSONDATA_SRE是空的。。。缓存未成功");
                HttpResquest.http("http://news-at.zhihu.com/api/4/news/latest", new HttpCalBackListener() {
                    @Override
                    public void onFnish(String responce) {
                        paresJsonMain(responce);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    public static TopicLin getTopicLin() {
        return topicLin;
    }

    public void paresJsonMenu(String jsonData) {
                Gson gson = new Gson();
                topicLin = gson.fromJson(jsonData, TopicLin.class);

        Log.d(TAG, "paresJsonMenu: litmit" + topicLin.getOthersClass().indexOf(this));
        Log.d(TAG, "paresJsonMenu: order" + topicLin.getSubscribed());
        Log.d(TAG, "paresJsonMenu: others" + topicLin.getOthersClass().get(2).getName());
        Log.d(TAG, "paresJsonMenu: id"+topicLin.getOthersClass().get(2).getId());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerViewMenu.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerViewMenu.setAdapter(new ThemeMenuAdapter(topicLin));
            }



        });
    }
    public void paresJsonMain(String jsonData) {
        Gson gson = new Gson();
        mainData = gson.fromJson(jsonData, MainData.class);
    
        Log.d(TAG, "paresJsonMain: "+mainData.getTop_stories().get(0).getImage());
        Log.d(TAG, "paresJsonMain: "+mainData.getTop_stories().size());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewPager.setInterval(5000);

                initViews();
                rollPagerChange=new RollPagerChange(layout,mainData,7,MainActivity.this);
                rollPagerChange.resumeScroll();
                viewPager.setOnPageChangeListener(rollPagerChange);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new MainNewsAdapter(mainData.getStories()));
                if(mainData.getDate()!=null)
                mainDateText.setText("今日热闻");
            }
        });
    }

    private void initViews(){
        imageViewList=new ArrayList<ImageView>();

//        TextView titleViewPager;
        ImageView dotView=null;
        layoutParams=new LinearLayout.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT,ViewPager.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin=1;

        imageView=new ImageView(getApplicationContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LoadPic.loadImg(MainActivity.this,mainData.getTop_stories().get(4).getImage(),imageView);

        imageViewList.add(0,imageView);

        for (int i=1;i<=5;i++){
            imageView=new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            if (i==4){
//                LoadPic.loadImg(MainActivity.this,mainData.getTop_stories().get(2).getImage(),imageView);
//            }
            LoadPic.loadImg(MainActivity.this,mainData.getTop_stories().get(i-1).getImage(),imageView);
            Log.d(TAG, "initViews: "+mainData.getTop_stories().get(i-1).getImage());
            imageViewList.add(i,imageView);

            final int id=i;
            switch (i){
                case 1:imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,EachMainNews.class);
                        intent.putExtra("MainNewsItem_id",mainData.getTop_stories().get(id).getId());
                        startActivity(intent);
                    }
                });
                    break;
                case 2:
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(MainActivity.this,EachMainNews.class);
                            intent.putExtra("MainNewsItem_id",mainData.getTop_stories().get(id).getId());
                            startActivity(intent);
                        }
                    });
                    break;
                case 3:
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1=new Intent(MainActivity.this,EachMainNews.class);
                            intent.putExtra("MainNewsItem_id",mainData.getTop_stories().get(id).getId());
                            startActivity(intent1);
                        }
                    });
                    break;
                case 4:
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1=new Intent(MainActivity.this,EachMainNews.class);
                            intent.putExtra("MainNewsItem_id",mainData.getTop_stories().get(id).getId());
                            startActivity(intent1);
                        }
                    });
                    break;
                case 5:
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1=new Intent(MainActivity.this,EachMainNews.class);
                            intent.putExtra("MainNewsItem_id",mainData.getTop_stories().get(id).getId());
                            startActivity(intent1);
                        }
                    });

            }
            imageViewList.add(imageView);
            viewPager.setCurrentItem(1,true);
            dotView=new ImageView(getApplicationContext());
//            titleViewPager=new TextView(getApplicationContext());
//            titleViewPager.setTextSize(20);
//            if(i==1){
//
//                dotView.setImageResource(R.drawable.page_now);
//            }else {
//                dotView.setImageResource(R.drawable.page);
//            }
            dotView.setTag(i);
            dotView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id=(Integer)v.getTag();
                    viewPager.setCurrentItem(id);
                }
            });
            layout.addView(dotView,layoutParams);
        }

        imageView=new ImageView(getApplicationContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LoadPic.loadImg(MainActivity.this,mainData.getTop_stories().get(0).getImage(),imageView);
        imageViewList.add(6,imageView);

        viewPager.setAdapter(new RollViewPagerAdapter(imageViewList));
        viewPager.setOffscreenPageLimit(7);
        viewPager.setCurrentItem(1,true);
        tttt.setText(mainData.getTop_stories().get(0).getTitle());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.HomeButtn:
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
//                drawerLayout.closeDrawer(Gravity.LEFT);
//                finish();
                break;
            case R.id.HomeText:
                Intent intentText=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intentText);
//                drawerLayout.closeDrawer(Gravity.LEFT);
                finish();
                break;
        }
    }
}
