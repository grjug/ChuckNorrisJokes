package com.grjug.android.chucknorrisjokes.api.controller;

import android.content.Context;

import com.android.volley.Response;
import com.grjug.android.chucknorrisjokes.api.dao.ChuckNorrisApiDao;
import com.grjug.android.chucknorrisjokes.api.util.JokeCallback;
import com.grjug.android.chucknorrisjokes.model.JokeResponse;
import com.grjug.android.chucknorrisjokes.model.UIJoke;

import org.json.JSONObject;

import timber.log.Timber;

import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;
import rx.functions.Func1;

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

    public Observable<UIJoke> fetchRandomJoke() {
                Timber.i("Retrieving joke...");
        return chuckNorrisService.fetchRandomJoke().map(new Func1<JokeResponse, UIJoke>() {
            @Override
            public UIJoke call(JokeResponse jokeResponse) {
                Timber.i("Found joke with id: %d", jokeResponse.getJoke().getId());
                return new UIJoke(jokeResponse);
            }
        });
    }

    public interface ChuckNorrisService {
        @GET("/jokes/random")
        public Observable<JokeResponse> fetchRandomJoke();
    }


}
