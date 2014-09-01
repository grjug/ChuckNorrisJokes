package com.grjug.android.chucknorrisjokes.model;

/**
 * Created by carlushenry on 8/31/14.
 */
public class UIJoke {

    private final JokeResponse jokeResponse;

    public UIJoke(JokeResponse jokeResponse) {
        this.jokeResponse = jokeResponse;
    }

    public String getJokeText() {
        return jokeResponse.getJoke().getJokeString();
    }
}
