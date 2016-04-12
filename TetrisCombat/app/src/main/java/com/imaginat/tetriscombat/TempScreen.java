package com.imaginat.tetriscombat;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.imaginat.tetriscombat.framework.Game;
import com.imaginat.tetriscombat.framework.Graphics;
import com.imaginat.tetriscombat.framework.Input.TouchEvent;
import com.imaginat.tetriscombat.framework.Pixmap;
import com.imaginat.tetriscombat.framework.Screen;
import com.imaginat.tetriscombat.framework.implementation.AccelerometerHandler;
import com.imaginat.tetriscombat.gameLogic.Board;
import com.imaginat.tetriscombat.gameLogic.GameLog;
import com.imaginat.tetriscombat.gameLogic.GameModel;
import com.imaginat.tetriscombat.gameLogic.Pieces;
import com.imaginat.tetriscombat.googleAPIHelper.GoogleHelper;

import java.util.List;
import java.util.Random;

/**
 * Created by nat on 3/20/16.
 */
public class TempScreen extends Screen {


    public static final int ATTACK_NO_SEE_CONTROLS=0;
    public static final int ATTACK_CLEAR_PROGRESS=1;
    public static final int ATTACK_BLOCK_VIEW=2;
    public static final int ATTACK_NO_CONTROLS=3;
    public static final int ATTACK_POWERS_MAX_INDEX=3;

    Random mRand  = new Random();
    class FakeButton {
        int x, y, width, length;
        boolean isActivated=false;

    }


    //for demo purposes
    FakeButton[] mCheatSheetButtons = new FakeButton[4];

    boolean mIsCheatSheetOn = false;

    Pieces mPieces = null;
    Board mBoard = null;
    GameModel mGameModel = null;

    int mMyScore = 0, mOpponentScore = 0;


    GameBoardFragment.ISendInfo isendInfo = null;

    TestBox theHorizontalTestBox;
    TestBox theVerticalTestBox;
    int deltaX = 1, deltaY;

    AccelerometerHandler mAccelHandler = null;//new AccelerometerHandler(game);
    static final float TICK_INITIAL = 1.5f;
    private boolean mDisplayControls = true;
    private boolean mBlockView = false;

    private long mHideControlsEndTime=0;
    private long mBlockViewEndTime=0;
    private long mAccelerometerEndTime=0;
    private boolean mLockOutDPad=false;


    public void toggleAccelerometer(){
        mAccelerometerEndTime=System.currentTimeMillis()+5000;
        mLockOutDPad=true;
    }
    public void toggleControls() {
        /*if (mDisplayControls) {
            mDisplayControls = false;
        } else {
            mDisplayControls = true;
        }*/
        mHideControlsEndTime=System.currentTimeMillis()+3000;
    }

    public void clearProgress() {
        isPaused = true;
        mBoard.clearBoard();
        isPaused = false;
        mMyScore = 0;
    }

    public void toggleBlockView() {

        mBlockViewEndTime=System.currentTimeMillis()+3000;

        /*if (mBlockView) {
            mBlockView = false;
        } else {
            mBlockView = true;
        }*/
    }

    public int getMyScore() {
        return mMyScore;
    }

    public void setMyScore(int myScore) {
        mMyScore = myScore;
    }

    public int getOpponentScore() {
        return mOpponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        mOpponentScore = opponentScore;
    }

    static final float TICK_DECREMENT = 0.5f;
    float tickTime = 0;
    static float tick = TICK_INITIAL;

    boolean isGameOver = false;
    boolean isPaused = false;
    boolean doIt = false;

    GoogleHelper mGoogleHelper = null;
    GameBoardFragment.ISendInfo iSendInfoInterface = null;

    public TempScreen(Game game) {
        super(game);

        mAccelHandler = new AccelerometerHandler(((GameBoardFragment)game).getContext());

        theHorizontalTestBox = new TestBox();
        theVerticalTestBox = new TestBox();
        theVerticalTestBox.setCurrentColor(Color.BLUE);
        theVerticalTestBox.x = 10;

        mPieces = new Pieces();
        mBoard = new Board(mPieces);
        mBoard.init();
        mGameModel = new GameModel(mBoard, mPieces);
        mGameModel.initGame();

        mGoogleHelper = GoogleHelper.forceGetInstance();

        for (int i = 0; i < mCheatSheetButtons.length; i++) {
            FakeButton b = new FakeButton();
            b.x = 320 - 60;
            b.y = 160 + (i * 60) + 2;
            b.width = b.length = 60;


            mCheatSheetButtons[i] = b;
        }
    }

    public Pixmap getPixmapAsset(int i){
        switch(i){
            case ATTACK_NO_SEE_CONTROLS:
                return Assets.controllerIcon;
            case ATTACK_CLEAR_PROGRESS:
                return Assets.clearBoardIcon;
            case ATTACK_BLOCK_VIEW:
                return Assets.blockViewIcon;
            case ATTACK_NO_CONTROLS:
                return Assets.noControlIcon;
            default:

                return null;
        }

    }
    public void setCommunicationInterface(GameBoardFragment.ISendInfo isendInfo) {
        this.isendInfo = isendInfo;
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
                if (inBounds(event, 320 - 75, 410, 65, 65)) {
                    Log.d("TempScreen", "Clicked Rotate1");
                    int tempRotation = (mGameModel.mRotation + 1) % 4;
                    if (mBoard.isPossibleMovement(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, tempRotation))
                        mGameModel.mRotation = tempRotation;

                }
            }


            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 10, 405, 60, 42) && !mLockOutDPad) {
                    Log.d("TempScreen", "Clicked Move Left");
                    if (mBoard.isPossibleMovement(mGameModel.mPosX - 1, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosX--;
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 70, 405, 60, 42) && !mLockOutDPad) {
                    Log.d("TempScreen", "Clicked Move Right");
                    if (mBoard.isPossibleMovement(mGameModel.mPosX + 1, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosX++;
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 30, 448, 80, 32)) {
                    Log.d("TempScreen", "Clicked Move down");
                    if (mBoard.isPossibleMovement(mGameModel.mPosY + 3, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosY += 3;
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 140, 420, 50, 50)) {
                    if (isPaused) {
                        Log.d("TempScreen", "UNPAUSED");
                        isPaused = false;
                    } else {
                        Log.d("TempScreen", "PAUSED");
                        isPaused = true;
                        mBoard.printBoard();
                    }
                    doIt = true;

                }
            }

            //if(mIsCheatSheetOn) {
            if (event.type == TouchEvent.TOUCH_UP) {
                for (int cc = 0; cc < mCheatSheetButtons.length; cc++) {
                    FakeButton b = mCheatSheetButtons[cc];

                    if (inBounds(event, b.x, b.y, b.width, b.length)) {
                        b.isActivated=true;
                        Log.d("TempScreen", "Button pressed " + cc);
                        isendInfo.sendData(cc);
                        mGameModel.mPlayerPowersCount[cc]--;
                        break;
                    }
                }

            }
            // }
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 320 - 60, 0, 60, 60)) {

                    //mBlockViewEndTime=System.currentTimeMillis()+3000;
                    //mHideControlsEndTime=System.currentTimeMillis()+3000;

                    //make something happend for at least five seconds


                    //int selectedPower= mRand.nextInt(ATTACK_POWERS_MAX_INDEX - 0 + 1) + 0;
                    for(int selectedPower=0;selectedPower<4;selectedPower++){
                        mGameModel.mPlayerPowersCount[selectedPower]++;
                    }


                    /*Log.d("TempScreen", "CHEAT SHEET");
                    if (mIsCheatSheetOn)
                        mIsCheatSheetOn = false;
                    else
                        mIsCheatSheetOn = true;*/


                }
            }



        }


        if (isPaused) {
            return;
        }
        tickTime += deltaTime;
        while (tickTime > tick) {
            long currentTime =  System.currentTimeMillis();
            if(mAccelerometerEndTime>currentTime) {
                float accel = mAccelHandler.getAccelX();
                //Log.d("accel","accel value is "+accel);
                if (accel < 0) {
                    if (mBoard.isPossibleMovement(mGameModel.mPosX + 1, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosX++;
                }

                if (accel > 0) {
                    if (mBoard.isPossibleMovement(mGameModel.mPosX - 1, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosX--;
                }
            }else{
                mLockOutDPad=false;
            }
            //if(doIt){
            tickTime -= tick;
            //progress the game piece down
            GameLog.log("--------------------------------BEGINNING OF TICK (after input) tickTime" + tickTime + " tick" + tick + "-------------------------------");
            //log += "\r\n+++Progress piece calculation+++";
            if (mBoard.isPossibleMovement(mGameModel.mPosX, mGameModel.mPosY + 1, mGameModel.mPiece, mGameModel.mRotation)) {
                GameLog.log("+++PROGRESS: movement is possible yPosition:" + (mGameModel.mPosY + 1));
                //board.printSelectedShapeBlocks(game.mPiece,game.mRotation);
                //mGameModel.drawPiece(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation);
                mGameModel.mPosY++;

            } else {
                GameLog.log("+++PROGRESS: movement of game piece not possible, current x,y is " + mGameModel.mPosX + " " + mGameModel.mPosY);
                mBoard.placePiece(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation, (byte) (mGameModel.mColor + 1));


                mBoard.deletePossibleLines();
                int totalDeleted=mBoard.getDeletedLineCount();
                if(totalDeleted>=4){
                    //UNLEASH A POWER RANDOMLY
                     int selectedPower= mRand.nextInt(ATTACK_POWERS_MAX_INDEX - 0 + 1) + 0;
                    mGameModel.mPlayerPowersCount[selectedPower]++;

                }
                //call to send info over
                Log.d("TempScreen", "About to call setScore and boradcastScore");
                //mGoogleHelper.setScore(mBoard.getDeletedLineCount());
                //GoogleHelper.getInstance().broadcastScore(false);
                isendInfo.communicate(mBoard.getScore());
                if (mBoard.isGameOver()) {
                    Log.d("TempScreen", "isGameOver is true");
                    int endGameReasoning = mBoard.getEndGameReasoning();
                    isendInfo.sendData(-100 + endGameReasoning);
                    return;


                }
                GameLog.log("++++++++CREATING new piece");
                //GameLog.getInstance().write(log);
                mGameModel.createNewPiece();


            }
            if (tick - TICK_DECREMENT > 0) {
                tick -= TICK_DECREMENT;
            }
            doIt = false;
        }


    }

    @Override
    public void present(float deltaTime) {
        long currentTime=System.currentTimeMillis();
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 322, 482, Color.BLACK);
        //draw lines
        for (int xStart = 10; xStart <= 260; xStart += 25) {
            g.drawLine(xStart, 0, xStart, 400, Color.GRAY);
        }
        for (int yStart = 0; yStart < 425; yStart += 25) {
            g.drawLine(10, yStart, 260, yStart, Color.GRAY);
        }
        //Log.d("TempScreen","color index is "+mGameModel.mColor);
        mBoard.paintMovingPiece(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation, GameModel.GAME_COLORS[mGameModel.mColor], g);
        mBoard.displayBoard(g);
        mBoard.paintNextPiece(mGameModel.mNextPiece, mGameModel.mNextRotation, GameModel.GAME_COLORS[mGameModel.mNextColor], g);

        // g.drawRect(320-60,0,60,480, Color.WHITE);
        //theHorizontalTestBox.render(g);
        //theVerticalTestBox.render(g);
        //g.drawLine(0,0,100,100, Color.RED);
        //g.drawRect(0,0,20,20,Color.RED);

        if (mBlockView || mBlockViewEndTime>currentTime) {
            g.drawRect(0, 200, 320, 200, Color.GRAY);
            Paint p = new Paint();
            p.setColor(Color.GREEN);
            g.drawText("SORRY!", 200, 220, p);
        }
        //input controls
        g.drawRect(0, 400, 320, 80, Color.BLACK);
        g.drawRect(0, 400, 320, 5, Color.DKGRAY);

        //rotate
        //g.drawRect(g.getWidth() - 75, 410, 65, 65, Color.BLUE);


//        //d-pad left
//        g.drawRect(10, 416, 32 ,42, Color.GREEN);
//        //d-pad right
//        g.drawRect(42+18, 416, 32, 42, Color.GRAY);
//        //d-pad down
//        g.drawRect(43,460,20,32,Color.RED);
//        //pause
//        g.drawRect(140,420,50,50,Color.BLUE);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        g.drawText("Me:" + mMyScore, 10, 20, paint);
        g.drawText("Opponent:" + mOpponentScore, 10, 40, paint);

        // g.drawPixmap(Assets.dPad, 10, 405);
        if(mHideControlsEndTime>currentTime){
            mDisplayControls=false;
        }else{
            mDisplayControls=true;
        }
        if (mDisplayControls) {
            //g.drawPixmap(Assets.dPad, 10, 405);
            g.drawPixmap(Assets.rotateButton, g.getWidth() - 75, 410);
            g.drawPixmap(Assets.pauseButton, 140, 420);

            //the controls
            g.drawPixmap(Assets.leftArrow, 10, 410);
            g.drawPixmap(Assets.rightArrow, 70, 410);
            g.drawPixmap(Assets.downArrow, 10, 445);

            if(mLockOutDPad){
                g.drawRect(10,410,100,50,Color.argb(200,240,240,240));
                g.drawText("SORRY, no D-Pad, tilt the screen instead",10,410,paint);
            }
        }


        //if (mIsCheatSheetOn) {
            for (int i = 0; i < mCheatSheetButtons.length; i++) {
                FakeButton b = mCheatSheetButtons[i];
                Pixmap p = getPixmapAsset(i);
                if(p==null) {
                    g.drawRect(b.x, b.y, b.width, b.length, Color.GRAY);
                   // g.drawRect(b.x,b.y,b.width,b.length,Color.argb(50,100,100,100));
                }else{
                    //if a pixmap exists in this slot

                    //1. draw the image
                    g.drawPixmap(p, b.x, b.y);


                    if(b.isActivated){
                        b.isActivated=false;
                       // g.drawRect(b.x,b.y,b.width,b.length,paint);
                    }
                    //2 right the number of instances left, if 0 gray it out
                    if(mGameModel.mPlayerPowersCount[i]<=0) {
                        g.drawRect(b.x, b.y, b.width, b.length, Color.argb(150, 200, 200, 200));
                        g.drawText("0", b.x + b.width - 20, b.y + b.length - 20, paint);

                    }else{
                        //just keeps track of theu number
                        g.drawText(mGameModel.mPlayerPowersCount[i]+"", b.x + b.width - 20, b.y + b.length - 20, paint);
                    }
                }


            }
        //}


        //g.drawRect(10, 410, 60, 42,Color.WHITE);
        //g.drawRect(70, 410, 60, 42,Color.WHITE);
        //g.drawRect(30, 448, 80, 32,Color.WHITE);

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

    public void toggleCheatSheet() {

    }
}

