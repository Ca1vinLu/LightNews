package com.example.lyz.appexercise.Network;

import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lyz.appexercise.SysApplication;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LYZ on 2016/12/2 0002.
 */

public class VolleyPost {


    //VolleyCallBack接口
    public interface VolleyCallback {
        void onSuccess(JSONObject result);
        void onFailure(VolleyError error);
    }

    public static void jsonObjectRequestPost(final String url, final Map<String, String> map, final VolleyCallback callback) {
        RequestQueue mQueue = SysApplication.getRequestQueue();
        String params = appendParameter(url, map);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
//                header.put("cookie", SysApplication.getInstance().getCookies());
                return header;
            }
        };
        mQueue.add(jsonObjectRequest);
    }


    public static void jsonObjectRequestGet(final String url, final Map<String, String> map, final VolleyCallback callback) {
        RequestQueue mQueue = SysApplication.getRequestQueue();
        String params = appendParameter(url, map);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
//                headers.put("cookie", SysApplication.getInstance().getCookies());
                return headers;
            }


        };
        mQueue.add(jsonObjectRequest);
    }


    private static String appendParameter(String url, Map<String, String> params) {
        Uri uri = Uri.parse(url);
        Uri.Builder builder = uri.buildUpon();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder.build().getQuery();
    }


}
