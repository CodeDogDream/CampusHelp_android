package com.dream.work.campushelp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.work.campushelp.Interface.OnItemClickListener;
import com.dream.work.campushelp.R;
import com.dream.work.campushelp.entity.MenuInfo;
import com.dream.work.campushelp.view.holder.MenuRecyclerViewHolder;

import java.util.ArrayList;

/**
 * Created by Dream on 2017/4/24.
 */

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewHolder> {
    private Context context;
    private ArrayList<MenuInfo> arrayList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MenuRecyclerViewAdapter(Context context, ArrayList<MenuInfo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public MenuRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_menu_item, parent, false);
        return new MenuRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MenuRecyclerViewHolder holder, int position) {
        holder.icon.setImageResource(arrayList.get(position).resId);
        holder.info.setText(arrayList.get(position).info);
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
