package com.imaginat.tetriscombat;

import android.graphics.Color;
import android.util.Log;

import com.imaginat.tetriscombat.framework.Game;
import com.imaginat.tetriscombat.framework.Graphics;
import com.imaginat.tetriscombat.framework.Input.TouchEvent;
import com.imaginat.tetriscombat.framework.Screen;

import java.util.List;

/**
 * Created by nat on 3/20/16.
 */
public class TempScreen extends Screen{

    TestBox theHorizontalTestBox;
    TestBox theVerticalTestBox;
    int deltaX=1,deltaY;
    public TempScreen(Game game){
        super(game);
        theHorizontalTestBox = new TestBox();
        theVerticalTestBox = new TestBox();
        theVerticalTestBox.setCurrentColor(Color.BLUE);
        theVerticalTestBox.x=10;
    }
    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        if(touchEvents.size()>0){
            Log.d("TempScreen","we have a touch event");
        }

        if(theHorizontalTestBox.x>235){
            theHorizontalTestBox.x=235;
            deltaX=-1;
        }else if(theHorizontalTestBox.x<=10){
            theHorizontalTestBox.x=10;
            deltaX=1;
        }
        theHorizontalTestBox.x+=deltaX;

        if(theVerticalTestBox.y>=375){
            theVerticalTestBox.y=375;
            deltaY=-1;
        }else if(theVerticalTestBox.y<=0){
            theVerticalTestBox.y=0;
            deltaY=1;
        }
        theVerticalTestBox.y+=deltaY;


    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawRect(0,0,322,482, Color.BLACK);
        //draw lines
        for(int xStart=10;xStart<=260;xStart+=25){
            g.drawLine(xStart,0,xStart,400,Color.GRAY);
        }
        for(int yStart=0;yStart<425;yStart+=25) {
            g.drawLine(10,yStart,260,yStart,Color.GRAY);
        }
        theHorizontalTestBox.render(g);
        theVerticalTestBox.render(g);
        //g.drawLine(0,0,100,100, Color.RED);
        //g.drawRect(0,0,20,20,Color.RED);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

