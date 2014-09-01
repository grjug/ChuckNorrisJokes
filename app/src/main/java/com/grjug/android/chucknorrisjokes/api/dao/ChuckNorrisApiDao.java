package com.grjug.android.chucknorrisjokes.api.dao;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.grjug.android.chucknorrisjokes.api.util.ChuckNorrisApiUtil;
import com.grjug.android.chucknorrisjokes.api.util.JokeCallback;
import com.grjug.android.chucknorrisjokes.model.LegacyJoke;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by foxefj on 3/17/14.
 */
public class ChuckNorrisApiDao {
    private ChuckNorrisApiUtil apiUtil;

    public ChuckNorrisApiDao(Context context)  {
        this.apiUtil = new ChuckNorrisApiUtil(context);
    }

    public void getJokeById(int id, final JokeCallback callback) {
        apiUtil.queueGetJokeById(id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    LegacyJoke legacyJoke = new LegacyJoke();

                    String jokeText = jsonObject.getJSONObject("value").getString("legacyJoke");
                    int jokeId = jsonObject.getJSONObject("value").getInt("id");

                    legacyJoke.setText(jokeText);
                    legacyJoke.setId(jokeId);
                    callback.success(legacyJoke);

                } catch (JSONException e) {
                    callback.failure(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callback.failure(volleyError.getMessage());
            }
        });
    }

    public void getNumberOfJokes(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetNumberOfJokes(responseListener, errorListener);
    }

    public void getCategories(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiUtil.queueGetCategories(responseListener, errorListener);
    }

}
