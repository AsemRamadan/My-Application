package com.miomio;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

/**
 * Kleiner Helfer zum Speichern/Anwenden der Theme-Auswahl (hell/dunkel).
 * Die Auswahl wird in SharedPreferences abgelegt, damit sie App-Neustarts ueberlebt.
 */
public final class ThemeHelper {

    private static final String PREFS = "miomio_settings";
    private static final String KEY_DARK_MODE = "dark_mode";

    private ThemeHelper() {
        // utility class
    }

    private static SharedPreferences prefs(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    /** @return true, wenn der dunkle Modus aktiv ist. */
    public static boolean isDarkMode(Context context) {
        return prefs(context).getBoolean(KEY_DARK_MODE, false);
    }

    /** Speichert die Auswahl und wendet sie sofort an. */
    public static void setDarkMode(Context context, boolean dark) {
        prefs(context).edit().putBoolean(KEY_DARK_MODE, dark).apply();
        applyMode(dark);
    }

    /** Wendet das gespeicherte Theme an (z. B. beim App-Start aufrufen). */
    public static void applyTheme(Context context) {
        applyMode(isDarkMode(context));
    }

    private static void applyMode(boolean dark) {
        AppCompatDelegate.setDefaultNightMode(
                dark ? AppCompatDelegate.MODE_NIGHT_YES
                     : AppCompatDelegate.MODE_NIGHT_NO);
    }
}
