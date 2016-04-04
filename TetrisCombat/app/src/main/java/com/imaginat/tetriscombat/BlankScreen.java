package com.imaginat.tetriscombat;

import com.imaginat.tetriscombat.framework.Game;
import com.imaginat.tetriscombat.framework.Screen;

/**
 * Created by nat on 4/4/16.
 */
public class BlankScreen extends Screen {

    public BlankScreen(Game game){
        super(game);
    }
    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {

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

    @Override
    public void setCommunicationInterface(GameBoardFragment.ISendInfo isendInfo) {

    }
}
