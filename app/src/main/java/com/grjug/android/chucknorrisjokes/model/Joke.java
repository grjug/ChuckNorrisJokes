package com.grjug.android.chucknorrisjokes.model;

import java.util.List;

/**
 * Created by emonk on 3/18/14.
 */
public class Joke {
    Integer id;
    String jokeText;
    List<String> categories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    public List<String> getCategories() { return categories; }

    public void setCategories(List<String> categories) {this.categories = categories;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Joke joke = (Joke) o;

        if (id != null ? !id.equals(joke.id) : joke.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
