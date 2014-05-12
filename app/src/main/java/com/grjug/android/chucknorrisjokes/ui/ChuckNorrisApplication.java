package com.grjug.android.chucknorrisjokes.ui;

import android.app.Application;

import com.grjug.android.chucknorrisjokes.persistence.DatabaseHelper;

/**
 * Created by carlushenry on 5/11/14.
 */
public class ChuckNorrisApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        DatabaseHelper db = new DatabaseHelper(this);
        db.createCategory("Nerdy");
        db.createCategory("Dirty");
    }
}
