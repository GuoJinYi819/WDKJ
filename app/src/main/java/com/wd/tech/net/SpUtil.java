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

    private static SpUtil instance;
    private final SharedPreferences user;
    private final SharedPreferences.Editor edit;

    private SpUtil(){
        user = App.context.getSharedPreferences("user", Context.MODE_PRIVATE);
        edit = user.edit();
    }

    public static SpUtil getInstance(){
        if (instance==null) {
            synchronized (SpUtil.class){
                if (instance == null) {
                    instance = new SpUtil();
                }
            }
        }
        return instance;
    }

    //缓存
    public void saveString(String key,String value){

        edit.putString(key, value);
        edit.commit();
    }
    public void saveInt(String key,int value){

        edit.putInt(key, value);
        edit.commit();
    }
    public void saveBoolean(String key,boolean value){
        edit.putBoolean(key, value);
        edit.commit();
    }
    //取缓存
    public String getSpString(String key){
        String string = user.getString(key, "");
        return string;
    }
    public int getSpInt(String key){
        int anInt = user.getInt(key, -1);
        return anInt;
    }
    public boolean getSpBoolean(String key){
        boolean aBoolean = user.getBoolean(key, false);
        return aBoolean;
    }
    //删除某个字段
    public void removeKey(String key){
        edit.remove(key);
        edit.commit();
    }
}
