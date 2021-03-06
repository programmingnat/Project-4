package com.imaginat.tetriscombat.framework;

import com.imaginat.tetriscombat.GameBoardFragment;

/**
 * Created by nat on 3/20/16.
 */
public abstract class Screen {
    protected final Game game;

    public Screen(Game game) {
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();

    public abstract void setCommunicationInterface(GameBoardFragment.ISendInfo isendInfo);
}
