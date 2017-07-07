package com.dream.work.campushelp.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.work.campushelp.Interface.OnItemClickListener;
import com.dream.work.campushelp.R;
import com.dream.work.campushelp.base.BaseFragment;
import com.dream.work.campushelp.entity.UserInfo;
import com.dream.work.campushelp.helper.Constants;
import com.dream.work.campushelp.helper.GlideImageLoader;
import com.dream.work.campushelp.helper.ToastHelper;
import com.dream.work.campushelp.network.HelpApiManager;
import com.dream.work.campushelp.network.HttpData;
import com.dream.work.campushelp.network.MySubscriber;
import com.dream.work.campushelp.network.bean.DataBean;
import com.dream.work.campushelp.utils.ParseJsonUtils;
import com.dream.work.campushelp.utils.RuntimeInfo;
import com.dream.work.campushelp.view.adapter.UserInfoAdapter;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

/**
 * Created by Dream on 2017/4/10.
 */

public class ModifyUserInfoFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private UserInfoAdapter adapter;
    private AlertDialog alertDialog;


    @Override
    public void bindView(View mRootView) {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        adapter = new UserInfoAdapter(thisActivity, new UserInfo());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(thisActivity));
        mRecyclerView.setAdapter(adapter);

    }

    private void sendRequestForUpdateUserInfo(String name, String date) {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
                if (dataBean.getCode() == 0) {
                    ToastHelper.showToast("修改成功");
                    sendRequestForUserInfo();
                }
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.UPDATE_USER_INFO, RuntimeInfo.getInstance().uid, name, date);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_info;
    }

    @Override
    public void onResume() {
        super.onResume();
        sendRequestForUserInfo();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initImagePicker();
        thisActivity.setTitle(R.string.modify_user_info);
        sendRequestForUserInfo();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                View edit = LayoutInflater.from(thisActivity).inflate(R.layout.dialog_input, null);
                final EditText editText = (EditText) edit.findViewById(R.id.edit);
                switch (position) {
                    case 0:
                        Intent intent = new Intent(thisActivity, ImageGridActivity.class);
                        thisActivity.startActivityForResult(intent, Constants.IMAGE_PICKER);
                        break;
                    case 1:
                        alertDialog = new AlertDialog.Builder(thisActivity)
                                .setTitle(R.string.nickname)
                                .setView(edit)
                                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (editText.getText().toString().trim().length() < 10) {
                                            sendRequestForUpdateUserInfo("uname", editText.getText().toString().trim());
                                            dialog.cancel();
                                        } else {
                                            ToastHelper.showToast("昵称过长");
                                        }
                                    }
                                })
                                .setNegativeButton(R.string.negative, null)
                                .show();
                        break;
                    case 2:
                        alertDialog = new AlertDialog.Builder(thisActivity)
                                .setTitle(R.string.intro)
                                .setView(edit)
                                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (editText.getText().toString().trim().length() < 25) {
                                            sendRequestForUpdateUserInfo("uintro", editText.getText().toString().trim());
                                            dialog.cancel();
                                        } else {
                                            ToastHelper.showToast("个性签名太长");
                                        }
                                    }
                                })
                                .setNegativeButton(R.string.negative, null)
                                .show();
                        break;
                    case 3:
                        alertDialog = new AlertDialog.Builder(thisActivity)
                                .setTitle(R.string.gender)
                                .setSingleChoiceItems(new String[]{"男", "女"}, 0, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (which == 0) {
                                            sendRequestForUpdateUserInfo("gender", "男");

                                        } else {
                                            sendRequestForUpdateUserInfo("gender", "女");
                                        }
                                        dialog.cancel();
                                    }
                                })
                                .show();
                        break;
                }
            }
        });
        if(alertDialog != null) {
            alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    sendRequestForUserInfo();
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setMultiMode(false);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    public void sendRequestForUserInfo() {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
                if (dataBean.getCode() == 0) {
                    UserInfo userInfo = ParseJsonUtils.getData(dataBean.getData(), UserInfo.class);
                    adapter.setUserInfo(userInfo);
                }
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.GET_USER_INFO, RuntimeInfo.getInstance().uid);
    }


}
