package com.grjug.android.chucknorrisjokes.util;

import com.grjug.android.chucknorrisjokes.api.controller.ChuckNorrisApiController;
import com.grjug.android.chucknorrisjokes.api.dao.ChuckNorrisApiDao;
import com.grjug.android.chucknorrisjokes.api.util.ChuckNorrisApiUtil;
import com.grjug.android.chucknorrisjokes.ui.ChuckNorrisApplication;
import com.grjug.android.chucknorrisjokes.ui.MainActivity;
import com.grjug.android.chucknorrisjokes.ui.RandomJokeActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlushenry on 8/31/14.
 */
@Module(
        injects = {
                ChuckNorrisApplication.class,
                MainActivity.class,
                RandomJokeActivity.class
        }
)
public class ChuckNorrisModule {
    private final ChuckNorrisApplication app;

    public ChuckNorrisModule(ChuckNorrisApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    ChuckNorrisApiController providesChuckNorrisApiController(ChuckNorrisApiDao apiDao) {
        return new ChuckNorrisApiController(apiDao);
    }

    @Provides
    @Singleton
    ChuckNorrisApiDao providesChuckNorrisApiDao(ChuckNorrisApiUtil apiUtil) {
        return new ChuckNorrisApiDao(apiUtil);
    }


    @Provides
    @Singleton
    ChuckNorrisApiUtil providesChuckNorrisApiUtil() {
        return new ChuckNorrisApiUtil(app);
    }
}
