package com.imaginat.tetriscombat;

import android.graphics.Color;
import android.graphics.Paint;

import com.imaginat.tetriscombat.framework.Game;
import com.imaginat.tetriscombat.framework.Graphics;
import com.imaginat.tetriscombat.framework.Screen;

/**
 * Created by nat on 4/7/16.
 */
public class GameOverScreen extends Screen {

    public static final int YOU_QUIT_GAME=0;
    public static final int OPPONENT_QUIT_GAME=1;
    public static final int YOU_WON_GAME=2;
    public static final int OPPONENT_WON_GAME=3;
    public static final int TIME_LIMIT_REACHED=4;


    private int mGameEndingReason=-1;

    public void setGameEndingReason(int gameEndingReason) {
        mGameEndingReason = gameEndingReason;
    }

    public GameOverScreen(Game game){
        super(game);
    }
    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        g.drawText("GAME OVER", 100,100,paint);

        switch(mGameEndingReason){
            case YOU_QUIT_GAME:
                g.drawText("You left the game!",60,120,paint);
                break;
            case OPPONENT_QUIT_GAME:
                g.drawText("YOU WON! Your opponent left the game!",60,120,paint);
                break;
            case YOU_WON_GAME:
                g.drawText("YOU WON! Congratulations!",60,120,paint);
                break;
            case OPPONENT_WON_GAME:
                g.drawText("YOU LOSS! Better luck next time!!",60,120,paint);
                break;
        }
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
