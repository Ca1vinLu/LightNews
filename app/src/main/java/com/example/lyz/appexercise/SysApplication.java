package com.example.lyz.appexercise;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by LYZ on 2017/5/15 0015.
 */

public class SysApplication extends Application {
    private static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue= Volley.newRequestQueue(this);
    }

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
