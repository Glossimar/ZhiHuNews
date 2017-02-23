package com.bignerdranch.android.zhihunews.Adapter;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.zhihunews.JSONData.ExtraData;
import com.bignerdranch.android.zhihunews.JSONData.LongCommitsData;
import com.bignerdranch.android.zhihunews.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by LENOVO on 2017/2/2.
 */

public class ExtraCommitsAdapter extends RecyclerView.Adapter<ExtraCommitsAdapter.ViewHolder> {
    private List<LongCommitsData.CommentsEntity> mLongCommitsDatas;
    private ExtraData mExtraDatas;
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        ImageView popularity;
        TextView userName;
        TextView popuNum;
        TextView commitText;
        View extraView;

        public ViewHolder (View view){
            super(view);
            extraView=view;
            imageView=(ImageView)view.findViewById(R.id.commit_circleView);
            popularity=(ImageView)view.findViewById(R.id.popularity);
            userName=(TextView)view.findViewById(R.id.commit_username);
            popuNum=(TextView)view.findViewById(R.id.int_popularity);
            commitText=(TextView)view.findViewById(R.id.commit_long);
        }
    }

    public ExtraCommitsAdapter(ExtraData extraDatas,List<LongCommitsData.CommentsEntity> longCommitsDatas){
        mExtraDatas=extraDatas;
        mLongCommitsDatas=longCommitsDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.longcommit_recycler,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.extraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"aaaaaa",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mLongCommitsDatas.size() != 0) {
            holder.commitText.setText(mLongCommitsDatas.get(position).getContent());
            holder.popuNum.setText(mLongCommitsDatas.get(position).getLikes() + "");
            holder.userName.setText(mLongCommitsDatas.get(position).getAuthor());
            holder.popularity.setImageResource(R.drawable.fabulous_grey);
            Glide.with(holder.extraView.getContext()).load(mLongCommitsDatas.get(position).getAvatar()).asBitmap()
                    .centerCrop().into(new BitmapImageViewTarget(holder.imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory
                            .create(holder.extraView.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.imageView.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mLongCommitsDatas.size();
    }
}
