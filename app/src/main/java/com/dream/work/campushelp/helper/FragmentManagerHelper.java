package com.dream.work.campushelp.helper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dream.work.campushelp.base.BaseActivity;


/**
 * Frgament辅助类
 * Created by Dream on 2016/4/7.
 */
public class FragmentManagerHelper {


    /**
     * 参见addFragment(activity, id, fragment, TAG, false, null)的注释
     *
     * @param activity
     * @param id
     * @param fragment
     */
    public static void addFragment(FragmentActivity activity, int id, Fragment fragment) {
        addFragment(activity, id, fragment, false, null);
    }

    /**
     * 参见addFragment(activity, id, fragment, TAG, false, liveMessage)的注释
     *
     * @param activity
     * @param id
     * @param fragment
     * @param bundle   需要发送至Fragment的数据
     */
    public static void addFragment(FragmentActivity activity, int id, Fragment fragment, Bundle bundle) {
        addFragment(activity, id, fragment, false, bundle);
    }

    /**
     * 参见addFragment(activity, id, fragment, TAG, stack, null)的注释
     *
     * @param activity
     * @param id
     * @param fragment
     * @param stack    将Fragment事务的当前状态加入栈，当为true时，按下返回键即可回到上一次的状态
     */
    public static void addFragment(FragmentActivity activity, int id, Fragment fragment, boolean stack) {
        addFragment(activity, id, fragment, stack, null);
    }

    /**
     * 增加一个Fragment实例到指定的Activity的id视图中
     * 通常建议首先调用removeFragment的方法来移除之前的Fragment
     *
     * @param activity Fragment所属的activity
     * @param id       需要将Fragment加入到视图的id
     * @param fragment 要加入的fragment
     * @param stack    将Fragment事务的当前状态加入栈，当为true时，按下返回键即可回到上一次的状态
     * @param bundle   需要发送至Fragment的数据
     */
    public static void addFragment(FragmentActivity activity, int id, Fragment fragment, boolean stack, Bundle bundle) {
        if (activity == null || activity.isFinishing() || fragment == null) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(id, fragment, fragment.getClass().getSimpleName());
        if (stack) fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }


    /**
     * 参见replaceFragment(activity, id, fragment, TAG, false)的注释
     *
     * @param activity
     * @param id
     * @param fragment
     */
    public static boolean replaceFragment(FragmentActivity activity, int id, Fragment fragment) {
        return replaceFragment(activity, id, fragment, false);
    }

    /**
     * 参见replaceFragment(activity, id, fragment, TAG, false, liveMessage)的注释
     *
     * @param activity
     * @param id
     * @param fragment
     * @param bundle   需要发送至Fragment的数据
     */
    public static boolean replaceFragment(FragmentActivity activity, int id, Fragment fragment, Bundle bundle) {
        return replaceFragment(activity, id, fragment, false, bundle);
    }

    /**
     * replaceFragment(activity, id, fragment, TAG, stack, null)的注释
     *
     * @param activity
     * @param id
     * @param fragment
     * @param stack    将Fragment事务的当前状态加入栈，当为true时，按下返回键即可回到上一次的状态
     */
    public static boolean replaceFragment(FragmentActivity activity, int id, Fragment fragment, boolean stack) {
        return replaceFragment(activity, id, fragment, stack, null);
    }

    /**
     * 增加一个Fragment实例到指定的Activity的id视图中
     * 并移除之前的存在的Fragment
     *
     * @param activity Fragment所属的activity
     * @param id       将Frgament加入到该视图中
     * @param fragment 要加入的fragment
     * @param stack    将Fragment事务的当前状态加入栈，当为true时，按下返回键即可回到上一次的状态
     * @param bundle   需要发送至Fragment的数据
     */
    public static boolean replaceFragment(FragmentActivity activity, int id, Fragment fragment, boolean stack, Bundle bundle) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName()) == null) {
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(id, fragment, fragment.getClass().getSimpleName());
            if (stack) fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }


    /**
     * 判断当前Tag的Fragment是否还存在
     *
     * @param activity
     * @param TAG
     * @return
     */
    public static boolean isFragmentAlive(FragmentActivity activity, String TAG) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(TAG) != null) {
            return true;
        }
        return false;
    }

    /**
     * 将一个fragment的实例从activity中移除
     * 首先会检查当前栈里是否存在这个Fragment，如果有，移除它。
     *
     * @param activity Fragment所属的activity
     * @param fragment 要加入的fragment
     * @return 当返回值为true时，代表已经移除，false则表示无需移除这个Frgment，因为不存在这个Frgament的实例
     */
    public static boolean removeFragment(FragmentActivity activity, Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragment != null && fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName()) != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment).commitAllowingStateLoss();
            return true;
        }

        return false;
    }

    public static boolean removeChildFragment(Fragment fragment, Fragment removeFragment) {
        FragmentManager fragmentManager = null;
        if (fragment != null) {
            fragmentManager = fragment.getChildFragmentManager();
        }
        if (fragmentManager.findFragmentByTag(removeFragment.getClass().getSimpleName()) != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(removeFragment).commitAllowingStateLoss();
            return true;
        }

        return false;
    }

    /**
     * 切换fragment，强烈不推荐使用匿名实例当作参数
     *
     * @param activity Fragment所属的activity
     * @param id       用来展示fragment的资源id
     * @param from     当前的fragment，即需要隐藏的fragment
     * @param to       需要显示的fragment
     */
    public static void switchFragment(FragmentActivity activity, int id, Fragment from, Fragment to) {
        if (from.getClass().getSimpleName().equals(to.getClass().getSimpleName())) return;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!to.isAdded()) {    // 先判断是否被add过
            fragmentTransaction.hide(from).add(id, to, from.getClass().getSimpleName()).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            fragmentTransaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
        }
    }

    public static void hideFragment(FragmentActivity activity, String tag) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
        }
    }

    public static void showFragment(FragmentActivity activity, String tag) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentTransaction.show(fragment).commit();
        }
    }
}
