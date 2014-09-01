package com.grjug.android.chucknorrisjokes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by carlushenry on 8/31/14.
 */
public class JokeResponse {
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private Joke joke;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
    }

    private class Joke {
        @Expose
        private Integer id;

        @SerializedName("joke")
        @Expose
        private String jokeString;
        @Expose
        private List<Category> categories;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getJokeString() {
            return jokeString;
        }

        public void setJokeString(String jokeString) {
            this.jokeString = jokeString;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }
    }

    private class Category {
        @Expose
        private List<String> categoryNames;

        public List<String> getCategoryNames() {
            return categoryNames;
        }

        public void setCategoryNames(List<String> categoryNames) {
            this.categoryNames = categoryNames;
        }
    }
}
