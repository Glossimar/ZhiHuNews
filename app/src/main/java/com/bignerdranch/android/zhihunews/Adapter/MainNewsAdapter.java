package com.bignerdranch.android.zhihunews.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.zhihunews.JSONData.MainData;
import com.bignerdranch.android.zhihunews.MainView.EachMainNews;
import com.bignerdranch.android.zhihunews.R;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/1/31.
 */

public class MainNewsAdapter extends RecyclerView.Adapter<MainNewsAdapter.ViewHolder> {
    private List<MainData.Stories> mStoriesList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View mainView;
        TextView mainTitle;

        public ViewHolder(View view){
            super(view);
            mainView=view;
            mainTitle=(TextView)view.findViewById(R.id.main_news_title);
        }
    }
    public MainNewsAdapter(List<MainData.Stories> storiesList){
        this.mStoriesList=storiesList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_news_recycler,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.mainTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Intent intent=new Intent(v.getContext(), EachMainNews.class);
                intent.putExtra("MainNewsItem_id",mStoriesList.get(position).getId());
                Log.d(TAG, "onClick: "+mStoriesList.get(position).getId());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MainNewsAdapter.ViewHolder holder, int position) {
        MainData.Stories oneStory=mStoriesList.get(position);
        holder.mainTitle.setText(oneStory.getTitle());
    }

    @Override
    public int getItemCount() {
        return mStoriesList.size();
    }
}
