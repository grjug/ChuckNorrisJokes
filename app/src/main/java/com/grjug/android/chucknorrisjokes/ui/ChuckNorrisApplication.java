package com.grjug.android.chucknorrisjokes.ui;

import android.app.Application;
import android.content.Context;

import com.grjug.android.chucknorrisjokes.persistence.DatabaseHelper;

import dagger.ObjectGraph;
import dagger.internal.Modules;

/**
 * Created by carlushenry on 5/11/14.
 */
public class ChuckNorrisApplication extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    public void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(Modules.list(this));
        objectGraph.inject(this);
    }

    public void inject(Object o) {
        objectGraph.inject(o);
    }

    public static ChuckNorrisApplication get(Context context) {
        return (ChuckNorrisApplication) context.getApplicationContext();
    }

    private void init() {
        DatabaseHelper db = new DatabaseHelper(this);
        db.createCategory("Nerdy");
        db.createCategory("Dirty");
    }
}
