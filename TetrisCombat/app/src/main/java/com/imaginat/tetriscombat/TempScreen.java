package com.imaginat.tetriscombat;

import android.graphics.Color;
import android.util.Log;

import com.imaginat.tetriscombat.framework.Game;
import com.imaginat.tetriscombat.framework.Graphics;
import com.imaginat.tetriscombat.framework.Input.TouchEvent;
import com.imaginat.tetriscombat.framework.Screen;
import com.imaginat.tetriscombat.gameLogic.Board;
import com.imaginat.tetriscombat.gameLogic.GameModel;
import com.imaginat.tetriscombat.gameLogic.Pieces;

import java.util.List;

/**
 * Created by nat on 3/20/16.
 */
public class TempScreen extends Screen {

    Pieces mPieces = null;
    Board mBoard = null;
    GameModel mGameModel = null;

    TestBox theHorizontalTestBox;
    TestBox theVerticalTestBox;
    int deltaX = 1, deltaY;

    static final float TICK_INITIAL = 1.5f;
    static final float TICK_DECREMENT = 0.05f;
    float tickTime = 0;
    static float tick = TICK_INITIAL;

    boolean isGameOver = false;
    boolean isPaused=false;

    public TempScreen(Game game) {
        super(game);
        theHorizontalTestBox = new TestBox();
        theVerticalTestBox = new TestBox();
        theVerticalTestBox.setCurrentColor(Color.BLUE);
        theVerticalTestBox.x = 10;

        mPieces = new Pieces();
        mBoard = new Board(mPieces);
        mBoard.init();
        mGameModel = new GameModel(mBoard, mPieces);
        mGameModel.initGame();
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(float deltaTime) {
        if (isGameOver) {
            return;
        }

        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();

        /*g.drawRect(0,450,60,30,Color.BLUE);
        g.drawRect(g.getWidth()-60,450,60,30,Color.BLUE);
        g.drawRect(61,450,100,30,Color.GREEN);
        g.drawRect(161, 450, 99, 30, Color.GRAY);*/



        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 450, 60, 30)) {
                    Log.d("TempScreen", "Clicked Rotate1");
                    int tempRotation = (mGameModel.mRotation + 1) % 4;
                    if (mBoard.isPossibleMovement(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, tempRotation))
                        mGameModel.mRotation = tempRotation;

                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, g.getWidth() - 60, 450, 60, 30)) {
                    Log.d("TempScreen", "Clicked Rotate2");
                    int tempRotation = Math.abs((mGameModel.mRotation - 1) % 4);
                    if (mBoard.isPossibleMovement(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, tempRotation))
                        mGameModel.mRotation = tempRotation;
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 61, 450, 100, 30)) {
                    Log.d("TempScreen", "Clicked Move Left");
                    if (mBoard.isPossibleMovement(mGameModel.mPosX - 1, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosX--;
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 161, 450, 99, 30)) {
                    Log.d("TempScreen", "Clicked Move Right");
                    if (mBoard.isPossibleMovement(mGameModel.mPosX + 1, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosX++;
                }
            }

            if(event.type==TouchEvent.TOUCH_UP){
                if(inBounds(event,0,400,65,65)){
                    if(isPaused){
                        Log.d("TempScreen","UNPAUSED");
                        isPaused=false;
                    }else{
                        Log.d("TempScreen","PAUSED");
                        isPaused=true;
                        mBoard.printBoard();
                    }

                }
            }

        }


        if(isPaused){
            return;
        }
        tickTime += deltaTime;
        while (tickTime > tick) {
            tickTime -= tick;
            //progress the game piece down
            Log.d("TempScreen","--------------------------------PROGRESS PIECE CALCULATION-------------------------------");
            //log += "\r\n+++Progress piece calculation+++";
            if (mBoard.isPossibleMovement(mGameModel.mPosX, mGameModel.mPosY + 1, mGameModel.mPiece, mGameModel.mRotation)) {
                Log.d("TempScreen","Progress: movement is possible tp " + (mGameModel.mPosY + 1));
                //board.printSelectedShapeBlocks(game.mPiece,game.mRotation);
                //mGameModel.drawPiece(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation);
                mGameModel.mPosY++;

            } else {
                Log.d("TempScreen","movement of game piece not possible, current x,y is "+mGameModel.mPosX+" "+mGameModel.mPosY);
                mBoard.placePiece(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation);

                mBoard.deletePossibleLines();

                /*if (mBoard.isGameOver()) {

                    System.out.println("GAME OVER");
                    //isGameOver = true;
                    System.exit(0);
                }*/
                Log.d("TempScreen","Creating new piece");
                //GameLog.getInstance().write(log);
                mGameModel.createNewPiece();

            }
            if (tick - TICK_DECREMENT > 0) {
                tick -= TICK_DECREMENT;
            }
        }

        /*if(theHorizontalTestBox.x>235){
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
*/

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 322, 482, Color.BLACK);
        //draw lines
        for (int xStart = 10; xStart <= 260; xStart += 25) {
            g.drawLine(xStart, 0, xStart, 400, Color.GRAY);
        }
        for (int yStart = 0; yStart < 425; yStart += 25) {
            g.drawLine(10, yStart, 260, yStart, Color.GRAY);
        }
        mBoard.paintMovingPiece(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation,g);
        mBoard.displayBoard(g);
        //theHorizontalTestBox.render(g);
        //theVerticalTestBox.render(g);
        //g.drawLine(0,0,100,100, Color.RED);
        //g.drawRect(0,0,20,20,Color.RED);

        //input controls
        g.drawRect(0, 400, 320, 80, Color.WHITE);
        g.drawRect(0, 450, 60, 30, Color.BLUE);
        g.drawRect(g.getWidth() - 60, 450, 60, 30, Color.BLUE);
        g.drawRect(61, 450, 100, 30, Color.GREEN);
        g.drawRect(161, 450, 99, 30, Color.GRAY);
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

