package ru.jollydroid.mvp1;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import javax.inject.Inject;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

    private FloatingActionButton fab;
// http://jollydroid.ru/notebook/2016-09-13-Dagger-2-MVP-Unit-Tests.html
    @Inject
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((DemoApplication)getApplication()).component().inject(this);
        String appname = resources.getString(R.string.app_name);
        Log.d("happy", "app name is " + appname);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().buttonPressed();
            }
        });
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return ((DemoApplication)getApplication()).component().mainPresenter();
    }

    @Override
    public void showCounter(int counter) {
        Snackbar
                .make(
                        fab,
                        "You clicked " + counter + " times",
                        Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();

    }
}
