package com.bignerdranch.android.zhihunews.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.zhihunews.Functions.LoadPic;
import com.bignerdranch.android.zhihunews.JSONData.MainData;
import com.bignerdranch.android.zhihunews.JSONData.TopicLin;
import com.bignerdranch.android.zhihunews.MainView.EachMainNews;
import com.bignerdranch.android.zhihunews.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/1/31.
 */

public class RollViewAdapter extends LoopPagerAdapter{
    private List<MainData.Top_Stories> mimagesAddress;
    private TextView mtextViewPager;
    private MainData mainData;

    public RollViewAdapter(RollPagerView viewPager, List<MainData.Top_Stories> storiesList
    , TextView textView, MainData mainData){
        super(viewPager);
        this.mimagesAddress=storiesList;
        this.mtextViewPager=textView;
        this.mainData=mainData;
    }
    public RollViewAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    @Override
    public View getView(final ViewGroup container, final int position) {
        final ImageView view= new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mtextViewPager.setText(mimagesAddress.get(position).getTitle());
        LoadPic.loadImgContext(container.getContext(),mimagesAddress.get(position).getImage(),view);
        return view;
    }

    @Override
    public int getRealCount() {
        return mimagesAddress.size();
    }
}
