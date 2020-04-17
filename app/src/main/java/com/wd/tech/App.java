package com.wd.tech;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/17 18:59
 * @classname :App
 */
public class App extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        //fresco初始化 自定义View
        initFresco();
    }

    private void initFresco() {
        DiskCacheConfig bwImage = DiskCacheConfig.newBuilder(context)
                .setMaxCacheSize(1024 * 1024 * 10)
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .setBaseDirectoryName("bwImage")
                .build();
        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(context)
                .setMainDiskCacheConfig(bwImage)
                .build();
        Fresco.initialize(context,build);
    }
}
