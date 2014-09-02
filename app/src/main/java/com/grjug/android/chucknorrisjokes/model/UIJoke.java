package com.grjug.android.chucknorrisjokes.model;

import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * Created by carlushenry on 8/31/14.
 */
public class UIJoke {

    private final JokeResponse jokeResponse;

    public UIJoke(JokeResponse jokeResponse) {
        this.jokeResponse = jokeResponse;
    }

    public String getJokeText() {
        String jokeString = jokeResponse.getJoke().getJokeString();
        return URLDecoder.decode(jokeString);
    }
}
