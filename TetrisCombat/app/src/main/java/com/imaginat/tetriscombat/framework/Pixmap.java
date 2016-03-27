package com.imaginat.tetriscombat.framework;

/**
 * Created by nat on 3/21/16.
 */
public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public Graphics.PixmapFormat getFormat();

    public void dispose();
}
