package com.bignerdranch.android.zhihunews.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.zhihunews.JSONData.TopLin_Theme;
import com.bignerdranch.android.zhihunews.R;
import com.bignerdranch.android.zhihunews.TopLin.EachThemeNews;

import java.util.List;

/**
 * Created by LENOVO on 2017/1/25.
 */

public class ThemeAdapterNonImage extends RecyclerView.Adapter<ThemeAdapterNonImage.ViewHolder>{
    private Context mContext;
    private List<TopLin_Theme.StoriesBean> mStoriesList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title;
        View view;
        public ViewHolder(View v){
            super(v);
            view=v;
            cardView=(CardView)v;
            title=(TextView)v.findViewById(R.id.nonpic_title_text);
        }
    }
    public ThemeAdapterNonImage(List<TopLin_Theme.StoriesBean> storiesList){
        mStoriesList=storiesList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.theme1_item_noimg,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                TopLin_Theme.StoriesBean storie=mStoriesList.get(position);
                Intent intent=new Intent(v.getContext(), EachThemeNews.class);
                intent.putExtra("EachThemeNewsId",mStoriesList.get(position).getId());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TopLin_Theme.StoriesBean stories=mStoriesList.get(position);
        holder.title.setText(stories.getTitle());
    }

    @Override
    public int getItemCount() {
        return mStoriesList.size();
    }
}
