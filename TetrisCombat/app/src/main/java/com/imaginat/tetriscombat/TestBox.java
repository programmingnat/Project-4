package com.imaginat.tetriscombat;

import android.graphics.Color;

import com.imaginat.tetriscombat.framework.Graphics;

/**
 * Created by nat on 3/21/16.
 */
public class TestBox {

    int mCurrentColor = Color.RED;

    public int x=0, y=0;
    public void render(Graphics g){
        g.drawRect(x,y,25,25, mCurrentColor);
        g.drawLine(x, y, x + 25, y, Color.WHITE);
        g.drawLine(x + 25, y, x + 25, y + 25, Color.WHITE);
        g.drawLine(x,y+25,x+25,y+25,Color.WHITE);
        g.drawLine(x,y,x,y+25,Color.WHITE);
    }

    public void setCurrentColor(int color){
        mCurrentColor=color;
    }
}
