package com.example.lyz.appexercise.Network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by LYZ on 2016/12/15 0015.
 */

public class JsonObjectRequest extends JsonRequest<JSONObject> {

    public JsonObjectRequest(String url, String stringRequest,
                             Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, stringRequest, listener, errorListener);
    }

    public JsonObjectRequest(int method, String url, String stringRequest,
                             Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, stringRequest, listener, errorListener);
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            JSONObject result = new JSONObject(jsonString);
//            String cookie=response.headers.get("set-cookie");
//            if(cookie!=null) {
//                int end = cookie.indexOf(";");
//                String cookies = cookie.substring(0, end);
//                result.put("cookie", cookies);
//            }
            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}