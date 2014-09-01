package com.grjug.android.chucknorrisjokes.api.util;
import com.grjug.android.chucknorrisjokes.model.LegacyJoke;

/**
 * Created by joshuakovach on 5/20/14.
 */
public interface JokeCallback {
    public void success(LegacyJoke legacyJoke);
    public void failure(String errorMessage);
}
