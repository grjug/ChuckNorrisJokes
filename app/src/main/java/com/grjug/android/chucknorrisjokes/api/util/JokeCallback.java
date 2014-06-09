package com.grjug.android.chucknorrisjokes.api.util;
import com.grjug.android.chucknorrisjokes.model.Joke;

/**
 * Created by joshuakovach on 5/20/14.
 */
public interface JokeCallback {
    public void success(Joke joke);
    public void failure(String errorMessage);
}
