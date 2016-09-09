package ru.jollydroid.mvp1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {
    private final DemoApplication application;

    public AndroidModule(DemoApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    public LayoutInflater providesLayoutInflater() {
        return LayoutInflater.from(application);
    }

    @Provides
    @Singleton
    public Resources providesResources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}

