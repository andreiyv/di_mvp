package ru.jollydroid.mvp1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by tse on 09/09/16.
 */
public class MainPresenterImplTest {

    private ClickCounterModel counter;
    private MainPresenterImpl presenter;
    private MainView view;

    @Before
    public void setUp() throws Exception {
        counter = mock(ClickCounterModel.class);
        view = mock(MainView.class);

        presenter = new MainPresenterImpl(counter);
    }

    @Test
    public void testButtonPressed() throws Exception {
        int count = 10;
        when(counter.getCount()).thenReturn(count);

        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);

        presenter.attachView(view);
        presenter.buttonPressed();

        verify(counter, times(1)).increaseCounter();
        verify(counter, atLeastOnce()).getCount();

        verify(view, times(1)).showCounter(intCaptor.capture());
        assertEquals((long)count, (long)intCaptor.getAllValues().get(0));
    }
}