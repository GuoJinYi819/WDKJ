package com.wd.tech.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.wd.tech.App;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/18 8:50
 * @classname :SpUtil
 */
public class SpUtil {
    //缓存
    public static void saveString(String key,String value){
        SharedPreferences user = App.context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putString(key, value);
        edit.commit();
    }
    public static void saveInt(String key,int value){
        SharedPreferences user = App.context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putInt(key, value);
        edit.commit();
    }
    public static void saveBoolean(String key,boolean value){
        SharedPreferences user = App.context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }
    //取缓存
    public static String getSpString(String key){
        SharedPreferences user = App.context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String string = user.getString(key, "");
        return string;
    }
    public static int getSpInt(String key){
        SharedPreferences user = App.context.getSharedPreferences("user", Context.MODE_PRIVATE);
        int anInt = user.getInt(key, -1);
        return anInt;
    }
    public static boolean getSpBoolean(String key){
        SharedPreferences user = App.context.getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean aBoolean = user.getBoolean(key, false);
        return aBoolean;
    }
}
