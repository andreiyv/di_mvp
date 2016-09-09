package ru.jollydroid.mvp1;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by tse on 12/08/16.
 */
public class MainPresenterImpl
    extends MvpBasePresenter<MainView>
    implements MainPresenter
{
    private final ClickCounterModel model;

    @Inject
    public MainPresenterImpl(ClickCounterModel model) {
        this.model = model;
    }

    @Override
    public void buttonPressed() {
        model.increaseCounter();

        if (isViewAttached()) {
            getView().showCounter(model.getCount());
        }
    }
}
