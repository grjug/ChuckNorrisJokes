package com.grjug.android.chucknorrisjokes.api.util;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.grjug.android.chucknorrisjokes.api.data.ChuckNorrisJoke;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by foxefj on 3/17/14.
 */
public class ChuckNorrisApiUtil {
    private RequestQueue requestQueue;

    public JSONObject getRandomJoke() {
        final JSONObject json;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, RANDOM_URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        json = jsonObject;
                    }
                },
        new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", response);
            }
        });
    }
}
