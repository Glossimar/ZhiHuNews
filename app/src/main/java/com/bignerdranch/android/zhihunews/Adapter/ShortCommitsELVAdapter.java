package com.bignerdranch.android.zhihunews.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.zhihunews.JSONData.ExtraData;
import com.bignerdranch.android.zhihunews.JSONData.LongCommitsData;
import com.bignerdranch.android.zhihunews.JSONData.ShortCommitsData;
import com.bignerdranch.android.zhihunews.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/2/3.
 */

public class ShortCommitsELVAdapter extends BaseExpandableListAdapter {
//    private String mParentName;
    private Context mContext;
    private ExtraData mExtraData;
    private List<ShortCommitsData.ShortCommentsEntity> mShortCommentsEntityList;

    public ShortCommitsELVAdapter(Context context, ExtraData extraData, List<ShortCommitsData.ShortCommentsEntity> shortCommentsEntityList){
//        this.mParentName=parentName;
        this.mContext=context;
        this.mExtraData=extraData;
        this.mShortCommentsEntityList=shortCommentsEntityList;
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mShortCommentsEntityList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mExtraData.getShort_comments();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mShortCommentsEntityList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=convertView;
        GroupHolder groupHolder=null;
        if (view==null){
            groupHolder=new GroupHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.commits_short_parent,null);
            groupHolder.groupImage=(ImageView)view.findViewById(R.id.commit_short_image);
            groupHolder.groupName=(TextView)view.findViewById(R.id.commit_short_title);
            view.setTag(groupHolder);
        }else{
            groupHolder=(GroupHolder)view.getTag();
        }
//        判断列表是否打开
        if (isExpanded){
            //如果打开了就用向上的箭头
            groupHolder.groupImage.setImageResource(R.drawable.up);
        }else {
//            如果没打开就用乡下的箭头
            groupHolder.groupImage.setImageResource(R.drawable.down);
        }

        groupHolder.groupName.setText(mExtraData.getShort_comments()+"条短评论");

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=convertView;
        ChildHolder childHolder = null;
        if (view==null) {
            childHolder=new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.commit_short_child, null);
            childHolder.popularityImage = (ImageView) view.findViewById(R.id.popularity_short);
            childHolder.userImage = (ImageView) view.findViewById(R.id.commit_short_circleView);
            childHolder.commitShort = (TextView) view.findViewById(R.id.commit_short);
            childHolder.popularityNum = (TextView) view.findViewById(R.id.int_popularity_short);
            childHolder.userName = (TextView) view.findViewById(R.id.commit_short_username);
            view.setTag(childHolder);
            Log.d(TAG, "getChildView: view==null是第一种情况撒");
        }else {
            childHolder=(ChildHolder)view.getTag();
            Log.d(TAG, "getChildView: view!=null是第二种情况撒");
        }

        childHolder.userName.setText(mShortCommentsEntityList.get(childPosition).getAuthor());
        childHolder.popularityNum.setText(mShortCommentsEntityList.get(childPosition).getLikes()+"");
        childHolder.commitShort.setText(mShortCommentsEntityList.get(childPosition).getContent());

        final ChildHolder finalChildHolder = childHolder;
        Glide.with(view.getContext()).load(mShortCommentsEntityList.get(childPosition).getAvatar()).asBitmap()
                .centerCrop().into(new BitmapImageViewTarget(finalChildHolder.userImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory
                        .create(view.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                finalChildHolder.userImage.setImageDrawable(circularBitmapDrawable);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupHolder{
        public TextView groupName;
        public ImageView groupImage;
    }
    class ChildHolder{
        public TextView userName;
        public TextView popularityNum;
        public TextView commitShort;
        public ImageView userImage;
        public ImageView popularityImage;
    }
}
