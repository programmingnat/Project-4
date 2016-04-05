package com.imaginat.tetriscombat;

import com.imaginat.tetriscombat.framework.Game;
import com.imaginat.tetriscombat.framework.Graphics;
import com.imaginat.tetriscombat.framework.Screen;

/**
 * Created by nat on 4/5/16.
 */
public class LoadingScreen extends Screen {

    TempScreen gameScreen;
    public LoadingScreen(Game game,TempScreen gameScreen){
        super(game);
        this.gameScreen=gameScreen;
    }
    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.dPad = g.newPixmap("dpad.png", Graphics.PixmapFormat.ARGB4444);
        Assets.rotateButton = g.newPixmap("rotatebutton.png", Graphics.PixmapFormat.ARGB4444);
        Assets.pauseButton = g.newPixmap("pausebutton.png", Graphics.PixmapFormat.ARGB4444);
        game.setScreen(gameScreen);
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
