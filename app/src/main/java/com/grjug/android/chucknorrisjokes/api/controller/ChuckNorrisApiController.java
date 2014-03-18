package com.grjug.android.chucknorrisjokes.api.controller;

import com.android.volley.Response;
import com.grjug.android.chucknorrisjokes.api.dao.ChuckNorrisApiDao;
import com.grjug.android.chucknorrisjokes.api.data.ChuckNorrisJokeData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by foxefj on 3/18/14.
 */
public class ChuckNorrisApiController {
    private ChuckNorrisApiDao apiDao;
    private boolean returned;
    private JSONObject jsonObject;
    private String errorMessage;

    public ChuckNorrisJokeData getRandomJoke() {
        JSONObject jsonObject = apiDao.getRandomJoke();
        return buildChuckNorrisJokeData(jsonObject);
    }

    public void getRandomJoke(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiDao.getRandomJoke(responseListener, errorListener);
    }

    public ChuckNorrisJokeData getJokeById(int id) {
        JSONObject jsonObject = apiDao.getJokeById(id);
        return buildChuckNorrisJokeData(jsonObject);
    }

    public void getJokeById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiDao.getJokeById(id, responseListener, errorListener);
    }

    private ChuckNorrisJokeData buildChuckNorrisJokeData(JSONObject jsonObject) {
        ChuckNorrisJokeData data = new ChuckNorrisJokeData();
        try {
            data.setId(jsonObject.getInt("id"));
            data.setJoke(jsonObject.getString("joke"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
