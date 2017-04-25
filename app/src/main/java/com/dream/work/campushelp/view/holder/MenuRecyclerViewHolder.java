package com.dream.work.campushelp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.work.campushelp.R;

/**
 * Created by Dream on 2017/4/24.
 */

public class MenuRecyclerViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView info;

    public MenuRecyclerViewHolder(View view) {
        super(view);
        icon = (ImageView) view.findViewById(R.id.recycler_view_icon);
        info = (TextView) view.findViewById(R.id.recycler_view_info);
    }
}
