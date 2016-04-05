package com.imaginat.tetriscombat;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.imaginat.tetriscombat.framework.Game;
import com.imaginat.tetriscombat.framework.Graphics;
import com.imaginat.tetriscombat.framework.Input.TouchEvent;
import com.imaginat.tetriscombat.framework.Screen;
import com.imaginat.tetriscombat.gameLogic.Board;
import com.imaginat.tetriscombat.gameLogic.GameLog;
import com.imaginat.tetriscombat.gameLogic.GameModel;
import com.imaginat.tetriscombat.gameLogic.Pieces;
import com.imaginat.tetriscombat.googleAPIHelper.GoogleHelper;

import java.util.List;

/**
 * Created by nat on 3/20/16.
 */
public class TempScreen extends Screen {

    class FakeButton{
        int x,y,width,length;
    }


    //for demo purposes
    FakeButton[] mCheatSheetButtons = new FakeButton[5];
    boolean mIsCheatSheetOn=false;

    Pieces mPieces = null;
    Board mBoard = null;
    GameModel mGameModel = null;

    int mMyScore=0,mOpponentScore=0;


    GameBoardFragment.ISendInfo isendInfo=null;

    TestBox theHorizontalTestBox;
    TestBox theVerticalTestBox;
    int deltaX = 1, deltaY;

    static final float TICK_INITIAL = 1.5f;
    private boolean mDisplayControls=true;
    private boolean mBlockView = false;
    public void toggleControls(){
        if(mDisplayControls){
            mDisplayControls=false;
        }else{
            mDisplayControls=true;
        }
    }

    public void clearProgress(){
        isPaused=true;
        mBoard.clearBoard();
        isPaused=false;
        mMyScore=0;
    }

    public void toggleBlockView(){
        if(mBlockView){
            mBlockView=false;
        }else{
            mBlockView=true;
        }
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
    boolean isPaused=false;
    boolean doIt=false;

    GoogleHelper mGoogleHelper=null;
    GameBoardFragment.ISendInfo iSendInfoInterface=null;

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

        mGoogleHelper = GoogleHelper.forceGetInstance();

        for(int i=0;i<mCheatSheetButtons.length;i++){
            FakeButton b = new FakeButton();
            b.x=320-60;
            b.y=60+(i*60)+2;
            b.width=b.length=60;
            mCheatSheetButtons[i] = b;
        }
    }

    public void setCommunicationInterface(GameBoardFragment.ISendInfo isendInfo){
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


        int theWidth = g.getWidth();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 320-75, 410, 65, 65)) {
                    Log.d("TempScreen", "Clicked Rotate1");
                    int tempRotation = (mGameModel.mRotation + 1) % 4;
                    if (mBoard.isPossibleMovement(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, tempRotation))
                        mGameModel.mRotation = tempRotation;

                }
            }








            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 10, 416, 32, 42)) {
                    Log.d("TempScreen", "Clicked Move Left");
                    if (mBoard.isPossibleMovement(mGameModel.mPosX - 1, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosX--;
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 60, 416, 32, 42)) {
                    Log.d("TempScreen", "Clicked Move Right");
                    if (mBoard.isPossibleMovement(mGameModel.mPosX + 1, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosX++;
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 43, 460, 20, 32)) {
                    Log.d("TempScreen", "Clicked Move down");
                    if (mBoard.isPossibleMovement(mGameModel.mPosY+ 1, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation))
                        mGameModel.mPosY++;
                }
            }

            if(event.type==TouchEvent.TOUCH_UP){
                if(inBounds(event,140,420,50,50)){
                    if(isPaused){
                        Log.d("TempScreen","UNPAUSED");
                        isPaused=false;
                    }else{
                        Log.d("TempScreen","PAUSED");
                        isPaused=true;
                        mBoard.printBoard();
                    }
                    doIt=true;

                }
            }

            if(mIsCheatSheetOn) {
                if (event.type == TouchEvent.TOUCH_UP) {
                    for (int cc = 0; cc < mCheatSheetButtons.length; cc++) {
                        FakeButton b = mCheatSheetButtons[cc];
                        if (inBounds(event, b.x, b.y, b.width, b.length)) {
                            Log.d("TempScreen", "Button pressed " + cc);
                            isendInfo.sendData(cc);
                            break;
                        }
                    }

                }
            }
            if(event.type==TouchEvent.TOUCH_UP){
                if(inBounds(event,320-60,0,60,60)){
                    Log.d("TempScreen","CHEAT SHEET");
                    if(mIsCheatSheetOn)
                        mIsCheatSheetOn=false;
                    else
                        mIsCheatSheetOn=true;


                }
            }





        }


        if(isPaused){
            return;
        }
        tickTime += deltaTime;
        while (tickTime > tick) {
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
                mBoard.placePiece(mGameModel.mPosX, mGameModel.mPosY, mGameModel.mPiece, mGameModel.mRotation);

                mBoard.deletePossibleLines();

                //call to send info over
                Log.d("TempScreen", "About to call setScore and boradcastScore");
                //mGoogleHelper.setScore(mBoard.getDeletedLineCount());
                //GoogleHelper.getInstance().broadcastScore(false);
                isendInfo.communicate(mBoard.getDeletedLineCount());
                if (mBoard.isGameOver()) {

                    isendInfo.sendData(-100);

                }
                GameLog.log("++++++++CREATING new piece");
                //GameLog.getInstance().write(log);
                mGameModel.createNewPiece();


            }
            if (tick - TICK_DECREMENT > 0) {
                tick -= TICK_DECREMENT;
            }
            doIt=false;
        }



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

        if(mBlockView){
            g.drawRect(0,200,320,200, Color.GRAY);
            Paint p = new Paint();
            p.setColor(Color.GREEN);
            g.drawText("SORRY!",200,220,p);
        }
        //input controls
        g.drawRect(0, 400, 320, 80, Color.WHITE);

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
        if(mDisplayControls) {
            g.drawPixmap(Assets.dPad, 10, 405);
            g.drawPixmap(Assets.rotateButton, g.getWidth() - 75, 410);
            g.drawPixmap(Assets.pauseButton, 140, 420);
        }


        if(mIsCheatSheetOn) {
            for (int i = 0; i < mCheatSheetButtons.length; i++) {
                FakeButton b = mCheatSheetButtons[i];
                g.drawRect(b.x, b.y, b.width, b.length, Color.GRAY);

            }
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

    public void toggleCheatSheet(){

    }
}

