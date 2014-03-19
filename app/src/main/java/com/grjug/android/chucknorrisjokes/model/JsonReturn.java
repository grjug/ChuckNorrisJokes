package com.grjug.android.chucknorrisjokes.model;

import java.util.List;

/**
 * Created by emonk on 3/18/14.
 */
public class JsonReturn {
    List<Joke> jokeList;
    List<String> categories;
    Integer count;

    public List<Joke> getJokeList() {
        return jokeList;
    }

    public void setJokeList(List<Joke> jokeList) {
        this.jokeList = jokeList;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
