package com.dream.work.campushelp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.work.campushelp.R;

/**
 * Created by Dream on 2017/6/6.
 */

public class HelpItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView avatar;
    public TextView userName;
    public TextView time;
    public TextView title;

    public HelpItemViewHolder(View view) {
        super(view);
        userName= (TextView) view.findViewById(R.id.user_name);
        avatar= (ImageView) view.findViewById(R.id.avatar);
        time= (TextView) view.findViewById(R.id.detail_info);
        title= (TextView) view.findViewById(R.id.my_title);
    }
}
