package com.miomio;

import android.app.Application;

/**
 * Application-Klasse: wendet beim Start das gespeicherte Theme (hell/dunkel) an.
 */
public class MioMioApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ThemeHelper.applyTheme(this);
    }
}
