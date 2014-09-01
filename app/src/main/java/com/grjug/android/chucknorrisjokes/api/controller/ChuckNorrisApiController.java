package com.grjug.android.chucknorrisjokes.api.controller;

import android.content.Context;

import com.android.volley.Response;
import com.grjug.android.chucknorrisjokes.api.dao.ChuckNorrisApiDao;
import com.grjug.android.chucknorrisjokes.api.util.JokeCallback;
import com.grjug.android.chucknorrisjokes.model.JokeResponse;

import org.json.JSONObject;

import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by foxefj on 3/18/14.
 */
public class ChuckNorrisApiController {
    private static ChuckNorrisApiController controller;
    private final ChuckNorrisService chuckNorrisService;
    private ChuckNorrisApiDao apiDao;

    private ChuckNorrisApiController(Context context) {
        apiDao = new ChuckNorrisApiDao(context);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.icndb.com")
                .build();

        chuckNorrisService = restAdapter.create(ChuckNorrisService.class);
    }

    public static ChuckNorrisApiController getInstance(Context context) {
        if (controller == null)
            controller = new ChuckNorrisApiController(context);

        return controller;
    }

    public Observable<JokeResponse> fetchRandomJoke() {
        return chuckNorrisService.fetchRandomJoke();
    }

    public void getJokeById(int id, JokeCallback callback) {
        apiDao.getJokeById(id, callback);
    }

    public void getNumberOfJokes(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiDao.getNumberOfJokes(responseListener, errorListener);
    }

    public void getCategories(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        apiDao.getCategories(responseListener, errorListener);
    }


    public interface ChuckNorrisService {
        @GET("/jokes/random")
        public Observable<JokeResponse> fetchRandomJoke();
    }


}
