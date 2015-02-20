package com.grjug.android.chucknorrisjokes.util;

import com.grjug.android.chucknorrisjokes.ui.ChuckNorrisApplication;

/**
 * Created by carlushenry on 8/31/14.
 */
public class Modules {
    public static Object[] list(ChuckNorrisApplication app) {
        return new Object[] {
                new ChuckNorrisModule(app)
//                ,new DebugU2020Module()
        };
    }

    private Modules() {
        // No instances.
    }
}
