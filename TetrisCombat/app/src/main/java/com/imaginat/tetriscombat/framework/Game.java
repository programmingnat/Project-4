package com.imaginat.tetriscombat.framework;

/**
 * Created by nat on 3/20/16.
 */
public interface Game {

    public Input getInput();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();
}
