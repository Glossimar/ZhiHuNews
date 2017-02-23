package com.bignerdranch.android.zhihunews.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.zhihunews.Functions.LoadPic;
import com.bignerdranch.android.zhihunews.JSONData.TopLin_Theme;
import com.bignerdranch.android.zhihunews.R;
import com.bignerdranch.android.zhihunews.TopLin.EditorData;
import com.example.picture.CirclelmageViewW;

import java.util.List;

/**
 * Created by LENOVO on 2017/2/14.
 */

public class EditorSpecificAdapter extends RecyclerView.Adapter<EditorSpecificAdapter.ViewHolder> {
    private TopLin_Theme mEditorsList;


    static class ViewHolder extends RecyclerView.ViewHolder{
        View editorView;
        TextView line;
        TextView editorName;
        TextView editorSpecific;
        CirclelmageViewW editorAvatar;

        public ViewHolder(View view){
            super(view);
            editorView=view;
            line=(TextView)view.findViewById(R.id.line1);
            editorAvatar=(CirclelmageViewW)view.findViewById(R.id.editor_avatar);
            editorName=(TextView)view.findViewById(R.id.editor_name);
            editorSpecific=(TextView)view.findViewById(R.id.editor_specific_data);
        }
    }

    public EditorSpecificAdapter(TopLin_Theme editorsList){
        this.mEditorsList=editorsList;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.editor_specific,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.editorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(EditorSpecificAdapter.ViewHolder holder, int position) {
        TopLin_Theme.EditorsBean editors=mEditorsList.getEditors().get(position);
        LoadPic.loadImgContext(holder.editorView.getContext(),editors.getAvatar(),holder.editorAvatar);
        holder.editorName.setText(editors.getName());
        holder.editorSpecific.setText(editors.getBio());
    }

    @Override
    public int getItemCount() {
        return mEditorsList.getEditors().size();
    }
}
