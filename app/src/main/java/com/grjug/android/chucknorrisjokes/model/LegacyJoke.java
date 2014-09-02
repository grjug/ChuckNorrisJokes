package com.grjug.android.chucknorrisjokes.model;

import java.util.List;

/**
 * Created by emonk on 3/18/14.
 */
public class LegacyJoke {
    public static final int RANDOM_ID = 0;

    Integer id;
    String text;
    List<String> categories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getCategories() { return categories; }

    public void setCategories(List<String> categories) {this.categories = categories;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegacyJoke legacyJoke = (LegacyJoke) o;

        if (id != null ? !id.equals(legacyJoke.id) : legacyJoke.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
