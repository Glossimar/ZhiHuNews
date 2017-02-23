package com.bignerdranch.android.zhihunews.Adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/2/21.
 */

public class RollViewPagerAdapter extends PagerAdapter {
    private List<ImageView> images;


    public RollViewPagerAdapter(List<ImageView> images){
        this.images=images;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(images.get(position));

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        if (container!=null){
            destroyItem(container,position,null);
        }
        View view=images.get(position);

        container.addView(view);
        return view;
    }



}
