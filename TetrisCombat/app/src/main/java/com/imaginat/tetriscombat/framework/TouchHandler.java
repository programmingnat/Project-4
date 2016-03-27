package com.imaginat.tetriscombat.framework;

import android.view.View;

import java.util.List;

/**
 * Created by nat on 3/21/16.
 */
public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<Input.TouchEvent> getTouchEvents();
}

