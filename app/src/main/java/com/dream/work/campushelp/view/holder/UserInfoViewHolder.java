package com.dream.work.campushelp.view.holder;

/**
 * Created by Dream on 2017/5/10.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.work.campushelp.R;

public class UserInfoViewHolder extends RecyclerView.ViewHolder {
    public TextView itemName;
    public TextView itemValue;
    public ImageView itemImage;
    public RelativeLayout mRootView;

    public UserInfoViewHolder(View view) {
        super(view);
        itemImage = (ImageView) view.findViewById(R.id.image_view);
        itemName = (TextView) view.findViewById(R.id.text1);
        itemValue = (TextView) view.findViewById(R.id.text_value);
        mRootView = (RelativeLayout) view.findViewById(R.id.root_view);
    }
}
