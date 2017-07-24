package com.janta.esir.megatips.app;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by esir on 10/07/2017.
 */

public class AppController extends Application{

    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue requestQueue;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance(){
        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        Log.e(TAG, " Check "+ requestQueue);
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        Log.e(TAG, " Check "+ requestQueue);
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String Tag){
        request.setTag(TextUtils.isEmpty(Tag) ? TAG : Tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request){
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag){
        if (requestQueue != null){
            requestQueue.cancelAll(tag);
        }
    }
}
