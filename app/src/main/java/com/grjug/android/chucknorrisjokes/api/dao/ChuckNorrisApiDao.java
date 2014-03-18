package com.grjug.android.chucknorrisjokes.api.dao;

import com.android.volley.Response;
import com.android.volley.VolleyError;
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

    public JSONObject getRandomJoke() {
        returned = false;
        apiUtil.queueGetRandomJoke(new ResponseListener(), new ErrorListener());

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

        return jsonObject;
    }

    public void getJokeById(int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetJokeById(id, responseListener, errorListener);
    }

    public JSONObject getJokeById(int id) {
        returned = false;
        apiUtil.queueGetJokeById(id, new ResponseListener(), new ErrorListener());

        int tryCount = 0;
        while (!returned) {
            try {
                Thread.sleep(100);
                tryCount++;

                if (tryCount > 5) {
                    throw new RuntimeException("Fetching sync joke timed out...");
                }
            } catch (Exception ex) {
                //log this...
            }
        }

        if (jsonObject == null) {
            throw new RuntimeException("Error fetching joke... " + errorMessage);
        }

        return jsonObject;
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
