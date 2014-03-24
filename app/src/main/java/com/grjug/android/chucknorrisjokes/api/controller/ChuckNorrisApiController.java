package com.grjug.android.chucknorrisjokes.api.controller;

import android.content.Context;

import com.android.volley.Response;
import com.grjug.android.chucknorrisjokes.api.dao.ChuckNorrisApiDao;

import org.json.JSONObject;

/**
 * Created by foxefj on 3/18/14.
 */
public class ChuckNorrisApiController {
    private static ChuckNorrisApiController controller;
    private ChuckNorrisApiDao apiDao;

    private ChuckNorrisApiController(Context context) {
        apiDao = new ChuckNorrisApiDao(context);
    }

    public static ChuckNorrisApiController getInstance(Context context) {
        if (controller == null)
            controller = new ChuckNorrisApiController(context);

        return controller;
    }

    public void getRandomJoke(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiDao.getRandomJoke(responseListener, errorListener);
    }

    public void getJokeById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiDao.getJokeById(id, responseListener, errorListener);
    }

    public void getNumberOfJokes(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiDao.getNumberOfJokes(responseListener, errorListener);
    }

    public void getCategories(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiDao.getCategories(responseListener, errorListener);
    }
}
