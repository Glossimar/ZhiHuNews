package com.bignerdranch.android.zhihunews.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.zhihunews.Functions.LoadPic;
import com.bignerdranch.android.zhihunews.JSONData.TopLin_Theme;
import com.bignerdranch.android.zhihunews.R;
import com.bignerdranch.android.zhihunews.TopLin.EditorData;
import com.example.picture.CirclelmageViewW;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/2/14.
 */

public class EditorRecyclerViewAdapter extends RecyclerView.Adapter<EditorRecyclerViewAdapter.ViewHolder> {
    private TopLin_Theme mTopLin_theme;
    private int mThemeId;


    static class ViewHolder extends RecyclerView.ViewHolder{
        View editorView;
        CirclelmageViewW editorlmage;

        public ViewHolder(View view){
            super(view);
            editorView=view;
            editorlmage=(CirclelmageViewW)view.findViewById(R.id.circleView);
        }
    }
    public EditorRecyclerViewAdapter(TopLin_Theme topLin_theme,int themeId){
        this.mTopLin_theme=topLin_theme;
        this.mThemeId=themeId;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.editor_recycler,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.editorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), EditorData.class);
                intent.putExtra("ThemeId",mThemeId);
                Log.d(TAG, "onClick: ......................"+mThemeId);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(EditorRecyclerViewAdapter.ViewHolder holder, int position) {
        TopLin_Theme.EditorsBean editors=mTopLin_theme.getEditors().get(position);
        LoadPic.loadImgContext(holder.editorView.getContext(),editors.getAvatar(),holder.editorlmage);
    }

    @Override
    public int getItemCount() {
        return mTopLin_theme.getEditors().size();
    }
}