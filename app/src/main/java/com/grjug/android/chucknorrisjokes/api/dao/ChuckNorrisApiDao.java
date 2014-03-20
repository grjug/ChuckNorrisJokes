package com.grjug.android.chucknorrisjokes.api.dao;

import com.android.volley.Response;
import com.grjug.android.chucknorrisjokes.api.util.ChuckNorrisApiUtil;

import org.json.JSONObject;

/**
 * Created by foxefj on 3/17/14.
 */
public class ChuckNorrisApiDao {
    private ChuckNorrisApiUtil apiUtil;
    private boolean returned;
    private JSONObject jsonObject;
    private String errorMessage;

    public void getRandomJoke(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetRandomJoke(responseListener, errorListener);
    }

    public void getJokeById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetJokeById(id, responseListener, errorListener);
    }

    public void getNumberOfJokes(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetNumberOfJokes(responseListener, errorListener);
    }

    public void getCategories(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetCategories(responseListener, errorListener);
    }
}
