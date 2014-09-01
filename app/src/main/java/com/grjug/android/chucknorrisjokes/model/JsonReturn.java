package com.grjug.android.chucknorrisjokes.model;

import java.util.List;

/**
 * Created by emonk on 3/18/14.
 */
public class JsonReturn {
    List<LegacyJoke> legacyJokeList;
    List<String> categories;
    Integer count;

    public List<LegacyJoke> getLegacyJokeList() {
        return legacyJokeList;
    }

    public void setLegacyJokeList(List<LegacyJoke> legacyJokeList) {
        this.legacyJokeList = legacyJokeList;
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
