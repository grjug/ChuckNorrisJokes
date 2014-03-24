package com.grjug.android.chucknorrisjokes.api.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by foxefj on 3/17/14.
 */
public class ChuckNorrisApiUtil {
    private RequestQueue requestQueue;
    private static final String RANDOM_URL = "http://api.icndb.com/jokes/random";
    private static final String GET_JOKE_URL = "http://api.icndb.com/jokes/";
    private static final String COUNT_URL = "http://api.icndb.com/jokes/count";
    private static final String CATEGORY_URL = "http://api.icndb.com/jokes/categories";

    public ChuckNorrisApiUtil(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void queueGetRandomJoke(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        queueRequest(RANDOM_URL, responseListener, errorListener);
    }

    public void queueGetJokeById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        if (id == 0)
            return;

        String url = GET_JOKE_URL + id;
        queueRequest(url, responseListener, errorListener);
    }

    public void queueGetNumberOfJokes(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        queueRequest(COUNT_URL, responseListener, errorListener);
    }

    public void queueGetCategories(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        queueRequest(CATEGORY_URL, responseListener, errorListener);
    }

    private void queueRequest(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);
        request.setShouldCache(false);
        requestQueue.add(request);
    }
}
