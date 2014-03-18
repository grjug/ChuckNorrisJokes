package com.grjug.android.chucknorrisjokes.api.dao;

import com.android.volley.Response;
import com.grjug.android.chucknorrisjokes.api.util.ChuckNorrisApiUtil;

import org.json.JSONObject;

/**
 * Created by foxefj on 3/17/14.
 */
public class ChuckNorrisApiDao {
    private ChuckNorrisApiUtil apiUtil;

    public void getRandomJoke(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetRandomJoke(responseListener, errorListener);
    }

    public void getJokeById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetJokeById(id, responseListener, errorListener);
    }
}
