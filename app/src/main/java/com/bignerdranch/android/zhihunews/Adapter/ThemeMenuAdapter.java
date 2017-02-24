package com.bignerdranch.android.zhihunews.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.zhihunews.JSONData.TopLin_Theme;
import com.bignerdranch.android.zhihunews.JSONData.TopicLin;
import com.bignerdranch.android.zhihunews.R;
import com.bignerdranch.android.zhihunews.TopLin.TopLin_theme;

/**
 * Created by LENOVO on 2017/2/13.
 */

public class ThemeMenuAdapter extends RecyclerView.Adapter<ThemeMenuAdapter.ViewHolder> {
    private TopicLin topicLin;
    private Activity activity;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View Arryview;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view){
            super(view);
            Arryview=view;
            imageView=(ImageView)view.findViewById(R.id.imageButton);
            textView=(TextView)view.findViewById(R.id.name_text);
        }
    }

    public ThemeMenuAdapter(TopicLin topicLin){
        this.topicLin=topicLin;
    }
    public ThemeMenuAdapter(TopicLin topicLin,Activity activity){
        this.topicLin=topicLin;
        this.activity=activity;
    }
    @Override
    public ThemeMenuAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_menu_name,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.Arryview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                TopicLin.OthersClass othersClass=topicLin.getOthersClass().get(position);
                Intent intent=new Intent(v.getContext(), TopLin_theme.class);
                intent.putExtra("intID",othersClass.getId());
                v.getContext().startActivity(intent);
                if (activity!=null){
                    activity.finish();
                }
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.imageView.getDrawable().getConstantState().equals
                        (v.getResources().getDrawable(R.drawable.add).getConstantState())){
                    int position=holder.getAdapterPosition();
                    Toast.makeText(parent.getContext(),"关注成功，关注内容会在首页呈现",Toast.LENGTH_SHORT).show();
                    TopicLin.OthersClass o=topicLin.getOthersClass().get(position);
                    topicLin.getOthersClass().remove(o);
                    notifyItemRemoved(position+1);
                    topicLin.getOthersClass().add(0,o);
                    notifyDataSetChanged();
                    topicLin.getOthersClass().get(0).setNum(2);
//                    holder.imageView.setImageResource(R.drawable.add_later);
                }else{
                    int position=holder.getAdapterPosition();
                    Toast.makeText(parent.getContext(),"取消关注",Toast.LENGTH_SHORT).show();
                    TopicLin.OthersClass o=topicLin.getOthersClass().get(position);
                    topicLin.getOthersClass().remove(o);
                    notifyItemRemoved(position+1);
                    topicLin.getOthersClass().add(topicLin.getOthersClass().size()-1,o);
                    notifyDataSetChanged();
                    topicLin.getOthersClass().get(topicLin.getOthersClass().size()-2).setNum(1);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ThemeMenuAdapter.ViewHolder holder, int position) {
        TopicLin.OthersClass othersClass=topicLin.getOthersClass().get(position);
        holder.imageView.setImageResource(R.drawable.add);
        holder.textView.setText(othersClass.getName());

        if (topicLin.getOthersClass().get(position).getNum()==2){
            holder.imageView.setImageResource(R.drawable.add_later);
        }
    }

    @Override
    public int getItemCount() {
        return topicLin.getOthersClass().size();
    }
}
