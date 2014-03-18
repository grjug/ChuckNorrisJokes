package com.grjug.android.chucknorrisjokes.api.controller;

import com.android.volley.Response;
import com.android.volley.VolleyError;
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
        returned = false;

        int tryCount = 0;
        while(!returned) {
            try {
                Thread.sleep(100);
                tryCount++;

                if (tryCount > 5) {
                    throw new RuntimeException("Fetching sync joke timed out...");
                }
            }
            catch(Exception ex) {
                //log this...
            }
        }

        if (jsonObject == null) {
            throw new RuntimeException("Error fetching joke... " + errorMessage);
        }

        return buildChuckNorrisJokeData(jsonObject);
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

    private class ResponseListener implements Response.Listener<JSONObject> {

        @Override
        public void onResponse(JSONObject jsonObject) {
            returned = true;
            jsonObject = jsonObject;
        }
    }

    private class ErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            returned = true;
            errorMessage = volleyError.getMessage();
        }
    }
}
