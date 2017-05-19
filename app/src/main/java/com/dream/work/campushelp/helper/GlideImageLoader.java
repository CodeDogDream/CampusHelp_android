package com.dream.work.campushelp.helper;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by Dream on 2017/5/9.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                .load(new File(path))
                .override(width, height)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}
