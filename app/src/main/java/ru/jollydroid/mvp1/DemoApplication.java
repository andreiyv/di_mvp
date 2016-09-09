package ru.jollydroid.mvp1;

import android.app.Application;

public class DemoApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent
                .builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public ApplicationComponent component() {
        return component;
    }
}
