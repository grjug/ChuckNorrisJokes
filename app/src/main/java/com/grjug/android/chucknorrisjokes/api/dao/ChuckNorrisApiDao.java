package com.grjug.android.chucknorrisjokes.api.dao;

import android.content.Context;

import com.android.volley.Response;
import com.grjug.android.chucknorrisjokes.api.util.ChuckNorrisApiUtil;

import org.json.JSONObject;

/**
 * Created by foxefj on 3/17/14.
 */
public class ChuckNorrisApiDao {
    private static ChuckNorrisApiUtil apiUtil;
    private static ChuckNorrisApiDao apiDao;

    private ChuckNorrisApiDao(Context context)  {
        this.apiUtil = ChuckNorrisApiUtil.getInstance(context);
    }

    public static ChuckNorrisApiDao getInstance(Context context) {
        if (apiDao == null)
            apiDao = new ChuckNorrisApiDao(context);

        return apiDao;
    }

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
