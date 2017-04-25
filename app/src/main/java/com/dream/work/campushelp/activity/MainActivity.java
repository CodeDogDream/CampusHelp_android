package com.dream.work.campushelp.activity;

import com.dream.work.campushelp.R;
import com.dream.work.campushelp.base.BaseActivity;
import com.dream.work.campushelp.fragment.first.UserFragment;
import com.dream.work.campushelp.helper.FragmentManagerHelper;

public class MainActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void event() {
        UserFragment useFragment = new UserFragment();
        FragmentManagerHelper.addFragment(thisActivity, R.id.activity_main_content, useFragment);
    }

}
