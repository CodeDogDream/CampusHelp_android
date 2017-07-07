package com.dream.work.campushelp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dream.work.campushelp.R;
import com.dream.work.campushelp.entity.HelpInfo;
import com.dream.work.campushelp.view.holder.HelpItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dream on 2017/6/6.
 */

public class HelpItemAdapter extends RecyclerView.Adapter<HelpItemViewHolder> {
    private List<HelpInfo> helpInfos;
    private Context context;

    public HelpItemAdapter(List<HelpInfo> helpInfos, Context context) {
        this.helpInfos = helpInfos;
        this.context = context;
    }

    @Override
    public HelpItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.help_info_item, parent, false);
        return new HelpItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HelpItemViewHolder holder, int position) {
        holder.title.setText(helpInfos.get(holder.getAdapterPosition()).getTitle());
        Glide.with(context).load(helpInfos.get(holder.getAdapterPosition()).getUavatar()).asBitmap().into(holder.avatar);
        holder.time.setText(String.valueOf(helpInfos.get(holder.getAdapterPosition()).getDate()));
        holder.userName.setText(helpInfos.get(holder.getAdapterPosition()).getUname());
    }

    @Override
    public int getItemCount() {
        return helpInfos.size();
    }

    public void setData(List<HelpInfo> helpInfos) {
        this.helpInfos = helpInfos;
        notifyDataSetChanged();
    }

    public void addData(List<HelpInfo> helpInfos) {
        if (this.helpInfos != null) {
            helpInfos.addAll(helpInfos);
            notifyDataSetChanged();
        } else {
            setData(helpInfos);
        }

    }
}
