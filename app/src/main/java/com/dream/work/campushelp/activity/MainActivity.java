package com.dream.work.campushelp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.work.campushelp.R;
import com.dream.work.campushelp.base.BaseActivity;
import com.dream.work.campushelp.base.BaseFragment;
import com.dream.work.campushelp.fragment.AboutUsFragment;
import com.dream.work.campushelp.fragment.AddHelpInfoFragment;
import com.dream.work.campushelp.fragment.HelpDetailFragment;
import com.dream.work.campushelp.fragment.MyHelpInfoFragment;
import com.dream.work.campushelp.fragment.NearByFragment;
import com.dream.work.campushelp.fragment.ModifyUserInfoFragment;
import com.dream.work.campushelp.helper.Constants;
import com.dream.work.campushelp.helper.FragmentManagerHelper;
import com.dream.work.campushelp.helper.ToastHelper;
import com.dream.work.campushelp.network.HelpApiManager;
import com.dream.work.campushelp.network.HttpData;
import com.dream.work.campushelp.network.MySubscriber;
import com.dream.work.campushelp.network.bean.DataBean;
import com.dream.work.campushelp.utils.Base64Utils;
import com.dream.work.campushelp.utils.RuntimeInfo;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;

import java.io.File;
import java.util.ArrayList;

import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private MainActivity thisActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thisActivity = this;
        event();
    }

    public void event() {
        View view = findViewById(R.id.top_arrow_back);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        relativeLayout = (RelativeLayout) findViewById(R.id.top_bar);
        BaseFragment baseFragment = null;
        String data = getIntent().getBundleExtra("bundle").getString(Constants.Page_Type);
        assert data != null;
        switch (data) {
            case Constants.USER_INFO:
                baseFragment = new ModifyUserInfoFragment();
                break;
            case Constants.NEARBY_INFO:
                baseFragment = new NearByFragment();
                break;
            case Constants.MY_HELP_INFO:
                baseFragment = new MyHelpInfoFragment();
                break;
            case Constants.ABOUT_US:
                baseFragment = new AboutUsFragment();
                break;
            case Constants.ADD_HELP_INFO:
                baseFragment = new AddHelpInfoFragment();
                break;
            case Constants.HELP_DETAIL_INFO:
                baseFragment = new HelpDetailFragment();
                baseFragment.setArguments(getIntent().getBundleExtra("bundle"));
                break;
            default:
                baseFragment = new ModifyUserInfoFragment();
                break;
        }
        FragmentManagerHelper.addFragment(thisActivity, R.id.activity_main_content, baseFragment);
    }

    public void setTitle(@StringRes int id) {
        ((TextView) relativeLayout.getChildAt(1)).setText(id);
    }

    public View getTitleView() {
        return relativeLayout.getChildAt(1);
    }

    public void setTitle(String title) {
        ((TextView) relativeLayout.getChildAt(1)).setText(title);
    }

    public View getSendText() {
        return relativeLayout.getChildAt(2);
    }

    public void sendRequestForUpdateAvatar(String base64) {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.UPDATE_AVATAR, RuntimeInfo.getInstance().uid, base64);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Constants.IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Bitmap bitmap = BitmapFactory.decodeFile(images.get(0).path);
                sendRequestForUpdateAvatar(Base64Utils.bitmapToBase64(bitmap));
            } else {
                Toast.makeText(this, "没有选择图片", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
