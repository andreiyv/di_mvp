package ru.jollydroid.mvp1;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by tse on 12/08/16.
 */
public class ClickCounterModel {
    private final SharedPreferences prefs;

    @Inject
    public ClickCounterModel(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public int getCount() {
        return prefs.getInt("counter", 0);
    }

    public void increaseCounter() {
        prefs.edit().putInt("counter", getCount() + 1).commit();
    }
}
