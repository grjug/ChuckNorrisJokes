package com.grjug.android.chucknorrisjokes.api.util;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by foxefj on 3/17/14.
 */
public class ChuckNorrisApiUtil {
    private RequestQueue requestQueue;
    private static final String RANDOM_URL = "http://api.icndb.com/jokes/random";
    private static final String GET_JOKE_URL = "http://api.icndb.com/jokes/";

    public void queueGetRandomJoke(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, RANDOM_URL, null, responseListener, errorListener);
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void queueGetJokeById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        if (id == 0)
            return;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GET_JOKE_URL + id, null, responseListener, errorListener);
        request.setShouldCache(false);
        requestQueue.add(request);
    }
}
