package com.dream.work.campushelp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dream.work.campushelp.Interface.OnItemClickListener;
import com.dream.work.campushelp.R;
import com.dream.work.campushelp.entity.UserInfo;
import com.dream.work.campushelp.view.holder.UserInfoViewHolder;

/**
 * Created by Dream on 2017/5/10.
 */

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoViewHolder> {
    private Context context;
    private UserInfo userInfo;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public UserInfoAdapter(Context context, UserInfo userInfo) {
        this.context = context;
        this.userInfo = userInfo;
    }

    @Override
    public UserInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_info_item, parent, false);
        UserInfoViewHolder holder = new UserInfoViewHolder(view);
        return holder;
    }

    public void setUserInfo(UserInfo info) {
        this.userInfo = info;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final UserInfoViewHolder holder, int position) {
        String[] info = context.getResources().getStringArray(R.array.user_info);
        holder.itemName.setText(info[position]);
        switch (position) {
            case 0:
                Glide.with(context).load(userInfo.getUavatar()).asBitmap().into(holder.itemImage);
                holder.itemImage.setVisibility(View.VISIBLE);
                holder.itemValue.setVisibility(View.GONE);
                break;
            case 1:
                holder.itemValue.setText(userInfo.getUname());
                break;
            case 2:
                holder.itemValue.setText(userInfo.getUintro());
                break;
            case 3:
                holder.itemValue.setText(userInfo.getGender());
                break;
        }
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(v, holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
