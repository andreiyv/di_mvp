package ru.jollydroid.mvp1;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by tse on 09/09/16.
 */
public class ClickCounterModelTest {
    SharedPreferences prefs;
    ClickCounterModel counter;

    @Before
    public void setUp() {
        prefs = mock(SharedPreferences.class);
        counter = new ClickCounterModel(prefs);
    }

    @Test
    public void testInitValue() {
        int count = 10;
        when(prefs.getInt(anyString(), anyInt())).thenReturn(count);

        int realCount = counter.getCount();

        assertEquals(count, realCount);
    }

    @SuppressLint("CommitPrefEdits")
    @Test
    public void testCount() {
        int count = 10;

        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        when(editor.putInt(anyString(), anyInt())).thenReturn(editor);

        when(prefs.getInt(anyString(), anyInt())).thenReturn(count);
        when(prefs.edit()).thenReturn(editor);

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);

        counter.increaseCounter();

        verify(prefs, times(1)).edit();
        verify(editor, times(1)).putInt(stringCaptor.capture(), intCaptor.capture());
        assertEquals((long)count + 1, (long)intCaptor.getAllValues().get(0));
        verify(editor, times(1)).commit();
        verifyNoMoreInteractions(editor);
    }
}