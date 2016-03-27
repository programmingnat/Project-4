package com.imaginat.tetriscombat.framework.implementation;

import android.graphics.Bitmap;

import com.imaginat.tetriscombat.framework.Graphics;
import com.imaginat.tetriscombat.framework.Pixmap;
import com.imaginat.tetriscombat.framework.Graphics.PixmapFormat;

/**
 * Created by nat on 3/21/16.
 */

public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}